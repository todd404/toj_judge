package com.todd.judger.judge.Judge;

import com.todd.judger.pojo.JudgeUuid;
import com.todd.judger.pojo.State;
import com.todd.judger.judge.StateMap;
import com.todd.judger.pojo.config.BackendServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public abstract class Judge {
    private JudgeUuid judgeUuid;
    private Double executeTime;
    private Integer memory;

    private Double resultExecuteTime;
    private Integer resultMemory;
    private Long problemId;
    private Integer historyId;
    @Autowired
    private StateMap stateMap;
    @Autowired
    private BackendServerConfig backendServerConfig;

    public StateMap getStateMap() {
        return stateMap;
    }

    public void setStateMap(StateMap stateMap) {
        this.stateMap = stateMap;
    }

    public BackendServerConfig getBackendServerConfig() {
        return backendServerConfig;
    }

    public void setBackendServerConfig(BackendServerConfig backendServerConfig) {
        this.backendServerConfig = backendServerConfig;
    }

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

    public Double getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Double executeTime) {
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
}
