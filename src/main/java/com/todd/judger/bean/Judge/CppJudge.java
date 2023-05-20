package com.todd.judger.bean.Judge;

import com.todd.judger.Model.JudgeUuid;
import com.todd.judger.Model.State;
import com.todd.judger.bean.StateMap;
import com.todd.judger.exception.CompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            throw new RuntimeException(e);
        }catch (CompleteException exception){
            setState(new State("complete error", exception.getMessage()));
        }
    }

    private void complete(String code) throws IOException, InterruptedException, CompleteException {
        setState(new State("completing", ""));
        File file = File.createTempFile(getJudgeUuid().getUuid(), ".cpp");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(code);
        fileWriter.close();

        File dir = new File(getJudgeUuid().getUuid());
        dir.mkdir();

        ProcessBuilder processBuilder = new ProcessBuilder("clang++",
                file.getAbsolutePath(),
                "-o", getJudgeUuid().getUuid() + "/" + "out.out");
        Process process = processBuilder.start();

        process.waitFor();
        if(process.exitValue() != 0){
            throw new CompleteException("f");
        }
    }

    private void run() throws IOException, InterruptedException {
        setState(new State("running", ""));
        File file = new File(getJudgeUuid().getUuid() + "/out.out");

        boolean test = file.setExecutable(true);

        ProcessBuilder processBuilder = new ProcessBuilder("/usr/bin/time",
                "-f", "%x, %E, %M",
                file.getAbsolutePath());

        File testFile = new File("/home/toddwu/test/test.txt");
        processBuilder.redirectInput(testFile);

        Process process = processBuilder.start();

        process.waitFor();
        String line = process.errorReader().readLine();

        while (line != null){
            System.out.println(line);
            line = process.errorReader().readLine();
        }

        line = process.inputReader().readLine();
        while (line != null){
            System.out.println(line);
            line = process.inputReader().readLine();
        }
    }
}
