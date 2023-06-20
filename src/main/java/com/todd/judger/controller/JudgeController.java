package com.todd.judger.controller;

import com.todd.judger.Model.JudgeUuid;
import com.todd.judger.Model.PostJudgeForm;
import com.todd.judger.Model.State;
import com.todd.judger.bean.Judge.Judge;
import com.todd.judger.bean.StateMap;
import com.todd.judger.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class JudgeController {
    @Autowired
    StateMap stateMap;

    @Autowired
    SpringContextHolder springContextHolder;

    @PostMapping("/judge")
    public JudgeUuid judge(@RequestBody PostJudgeForm form){
        JudgeUuid judgeUuid = new JudgeUuid(UUID.randomUUID());

        String judgeName = form.getLanguage() + "Judge";

        Judge judge = springContextHolder.getJudge(judgeName);

        judge.setJudgeUuid(judgeUuid);
        judge.setExecuteTime(form.getExecuteTime());
        judge.setMemory(form.getMemory());
        judge.setProblemId(form.getProblemId());
        judge.setHistoryId(form.getHistoryId());

        judge.judgeCode(form.getProblemId(), form.getCode());

        stateMap.putState(judgeUuid.getUuid(), new State("queuing", "排队中..."));

        return judgeUuid;
    }

    @GetMapping("/state")
    @CrossOrigin
    public State getState(@RequestParam("uuid") String uuid){
        State result = new State("error uuid", "判题任务出错...");
        if(!stateMap.hasUuid(uuid)){
            return result;
        }

        result = stateMap.getState(uuid);

        return result;
    }
}
