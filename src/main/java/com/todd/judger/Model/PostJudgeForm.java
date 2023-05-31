package com.todd.judger.Model;


public class PostJudgeForm {
    private long problemId;
    private String code;
    private String language;
    private Integer execute_time;
    private Integer memory;
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

    public Integer getExecute_time() {
        return execute_time;
    }

    public void setExecute_time(Integer execute_time) {
        this.execute_time = execute_time;
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
