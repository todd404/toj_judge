package com.todd.judger.Model;

import java.util.UUID;

public class JudgeUuid {
    private String uuid;

    public JudgeUuid(UUID uuid){
        this.uuid = String.valueOf(uuid);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
