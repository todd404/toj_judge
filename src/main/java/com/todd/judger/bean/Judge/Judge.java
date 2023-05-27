package com.todd.judger.bean.Judge;

import com.todd.judger.Model.JudgeUuid;
import com.todd.judger.Model.State;
import com.todd.judger.bean.StateMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public abstract class Judge {
    private JudgeUuid judgeUuid;
    @Autowired
    private StateMap stateMap;

    public JudgeUuid getJudgeUuid() {
        return judgeUuid;
    }

    public void setJudgeUuid(JudgeUuid judgeUuid) {
        this.judgeUuid = judgeUuid;
    }

    public void setState(State state){
        stateMap.setState(judgeUuid.getUuid(), state);
    }
    public abstract void judgeCode(long problemId, String code);

}
