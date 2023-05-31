package com.todd.judger.bean.Judge;

import com.todd.judger.Model.State;
import com.todd.judger.exception.CompleteException;
import com.todd.judger.exception.RunningException;
import com.todd.judger.util.MyUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Component("cppJudge")
@Scope("prototype")
public class CppJudge extends Judge{

    @Override
    @Async
    public void judgeCode(long problemId, String code) {
        try{
            complete(code);
            run();
        }catch (IOException | InterruptedException e) {
            putState(new State("error", "未知错误，请联系管理员"));
        }catch (CompleteException exception){
            putState(new State("complete error", exception.getMessage()));
        }catch (RunningException exception){
            putState(new State("running error", exception.getMessage()));
        }finally {

        }
    }

    private void complete(String code) throws IOException, InterruptedException, CompleteException {
        putState(new State("completing", "编译中..."));
        File tempCodeFile = File.createTempFile(getJudgeUuid().getUuid(), ".cpp");
        FileWriter fileWriter = new FileWriter(tempCodeFile);
        fileWriter.write(code);
        fileWriter.close();

        File uuidDir = new File(getJudgeUuid().getUuid());
        uuidDir.mkdir();

        ProcessBuilder processBuilder = new ProcessBuilder("clang++",
                tempCodeFile.getAbsolutePath(),
                "-o", uuidDir.getPath() + "/" + "out.out");
        Process process = processBuilder.start();

        process.waitFor();
        if(process.exitValue() != 0){
            throw new CompleteException("编译错误...");
        }
    }

    private void run() throws IOException, InterruptedException, RunningException {
        putState(new State("running", "运行中..."));
        File file = new File(getJudgeUuid().getUuid() + "/out.out");

        boolean setExecutableResult = file.setExecutable(true);
        if(!setExecutableResult){
            throw new RunningException("设置程序执行失败...");
        }

        downloadFiles();

        File testFile = new File(getJudgeUuid().getUuid() + "/test.txt");
        File answerFile = new File(getJudgeUuid().getUuid() + "/answer.txt");

        ProcessBuilder processBuilder = new ProcessBuilder(
                "/usr/bin/time",
                "-f", "%e,%M",
                file.getAbsolutePath());

        processBuilder.redirectInput(testFile);

        Process process = processBuilder.start();

        var waitResult = process.waitFor(getExecuteTime(), TimeUnit.MILLISECONDS);
        if(!waitResult){
            //超时
            throw new RunningException("超过运行时间限制！");
        }

        //TODO: 是否要加个返回值是否为0的判断？是否与下面的错误检查重复？

        //通过检查err流检查运行是否有错误
        //注意：time指令的输出也在错误流里，在最后一行，如果错误流一行也没有说明运行异常
        String errorLine ;
        ArrayList<String> errorLineList = new ArrayList<>();
        while ((errorLine = process.errorReader().readLine()) != null){
            errorLineList.add(errorLine);
        }

        if(errorLineList.size() > 1){
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < errorLineList.size() - 1; i++){
                result.append(errorLineList.get(i)).append("\n");
            }
            throw new RunningException(result.toString().trim());
        }

        //获取time指令输出，判断是否超时或超内存
        String timeLine = errorLineList.get(errorLineList.size() - 1);
        if(timeLine != null){
            var splitTime = timeLine.split(",");
            double executeTime = Double.parseDouble(splitTime[0]);
            Integer memory = Integer.valueOf(splitTime[1]);

            if(executeTime > getExecuteTime()){
                throw new RunningException("超过运行时间限制！");
            }

            if(memory > getMemory()){
                throw new RunningException("超过内存限制！");
            }
        }else{
            throw new RunningException("未知错误请联系管理员！");
        }

        //判题
        BufferedReader inputReader = process.inputReader();
        Scanner answerScanner = new Scanner(answerFile);
        String resultLine;
        while ((resultLine = inputReader.readLine()) != null){
            String trimResultLine = resultLine.trim();
            if(trimResultLine.isEmpty()){
                //跳过空行，让判题体验更好，也就是输出格式不一定要和答案完全一致，内容一致即可。
                continue;
            }

            if(answerScanner.hasNext()){
                String answerLine = answerScanner.nextLine().trim();
                if(trimResultLine.equals(answerLine)){
                    continue;
                }else{
                    throw new RunningException("答案错误!");
                }
            }else{
                throw new RunningException("答案错误!");
            }
        }

        if(answerScanner.hasNext()){
            throw new RunningException("答案错误!");
        }

        putState(new State("success", "通过"));
    }

    private void downloadFiles() throws IOException, RunningException {
        String testFileUrl = String.format("http://%s/%s.txt", "localhost:5000", getProblemId());
        String answerFileUrl = String.format("http://%s/%s.txt", "localhost:5001", getProblemId());

        File testFile = new File(getJudgeUuid().getUuid() + "/test.txt");
        File answerFile = new File(getJudgeUuid().getUuid() + "/answer.txt");

        if(!testFile.createNewFile() || !answerFile.createNewFile()){
            throw new RunningException("创建对应文件失败...");
        }

        MyUtils.downloadFile(testFileUrl, testFile.getPath());
        MyUtils.downloadFile(answerFileUrl, answerFile.getPath());
    }
}
