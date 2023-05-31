package com.todd.judger.bean.Judge;

import com.todd.judger.Model.JudgeUuid;
import com.todd.judger.Model.State;
import com.todd.judger.bean.StateMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class Judge {
    private JudgeUuid judgeUuid;
    private Integer executeTime;
    private Integer memory;
    private Long problemId;
    @Autowired
    private StateMap stateMap;

    public void putState(State state){
        stateMap.putState(judgeUuid.getUuid(), state);
    }

    public abstract void judgeCode(long problemId, String code);

    public JudgeUuid getJudgeUuid() {
        return judgeUuid;
    }

    public void setJudgeUuid(JudgeUuid judgeUuid) {
        this.judgeUuid = judgeUuid;
    }

    public Integer getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Integer executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }
}
