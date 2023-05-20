package com.todd.judger.Model;

public class PostJudgeForm {
    private long problemId;
    private String code;
    private String language;

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

    @Override
    public String toString() {
        return "PostJudgeForm{" +
                "problemId=" + problemId +
                ", code='" + code + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
