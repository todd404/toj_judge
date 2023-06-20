package com.todd.judger.Model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PostJudgeForm {
    @JsonProperty("problem_id")
    private long problemId;
    private String code;
    private String language;
    @JsonProperty("execute_time")
    private Integer executeTime;
    private Integer memory;
    @JsonProperty("history_id")
    private Integer historyId;

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }
}
