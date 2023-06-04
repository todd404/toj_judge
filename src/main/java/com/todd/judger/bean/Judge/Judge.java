package com.todd.judger.bean.Judge;

import com.todd.judger.Model.JudgeUuid;
import com.todd.judger.Model.State;
import com.todd.judger.bean.StateMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public abstract class Judge {
    private JudgeUuid judgeUuid;
    private Integer executeTime;
    private Integer memory;

    private Double resultExecuteTime;
    private Integer resultMemory;
    private Long problemId;
    private Integer historyId;
    @Autowired
    private StateMap stateMap;

    public void putState(State state){
        stateMap.putState(judgeUuid.getUuid(), state);
    }
    public State getState(String uuid){ return stateMap.getState(uuid); }

    public abstract void judgeCode(long problemId, String code);
    protected abstract void reportResult() throws URISyntaxException, IOException, InterruptedException;
    protected abstract void cleanUuidDir();
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

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Double getResultExecuteTime() {
        return resultExecuteTime;
    }

    public void setResultExecuteTime(Double resultExecuteTime) {
        this.resultExecuteTime = resultExecuteTime;
    }

    public Integer getResultMemory() {
        return resultMemory;
    }

    public void setResultMemory(Integer resultMemory) {
        this.resultMemory = resultMemory;
    }

    public URI getReportUri() throws URISyntaxException {
        return new URI(String.format("http://192.168.31.168:8080/api/set-state"));
    }
}
