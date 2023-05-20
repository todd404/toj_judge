package com.todd.judger.controller;

import com.todd.judger.Model.JudgeUuid;
import com.todd.judger.Model.PostJudgeForm;
import com.todd.judger.Model.State;
import com.todd.judger.bean.Judge.CppJudge;
import com.todd.judger.bean.Judge.Judge;
import com.todd.judger.bean.StateMap;
import com.todd.judger.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class JudgeController {
    @Autowired
    StateMap stateMap;

    @Autowired
    SpringContextHolder springContextHolder;

    @PostMapping("/api/judge")
    public JudgeUuid judge(@ModelAttribute PostJudgeForm form){
        JudgeUuid judgeUuid = new JudgeUuid(UUID.randomUUID());

        Judge judge = springContextHolder.getJudge("cppJudge");
        judge.setJudgeUuid(judgeUuid);
        judge.judgeCode(form.getProblemId(), form.getCode());

        stateMap.setState(judgeUuid.getUuid(), new State("queuing", ""));

        return judgeUuid;
    }

    @GetMapping("/api/state")
    public State getState(@RequestParam("uuid") String uuid){
        State result = new State("wrong uuid", "");
        if(!stateMap.hasUuid(uuid)){
            return result;
        }

        result = stateMap.getState(uuid);

        return result;
    }
}
