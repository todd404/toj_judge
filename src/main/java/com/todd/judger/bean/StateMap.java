package com.todd.judger.bean;


import com.todd.judger.Model.State;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StateMap {
    private static final Map<String, State> state = new HashMap<>();
    public void setState(String uuid, State s){
        state.put(uuid, s);
    }

    public State getState(String uuid){
        return state.get(uuid);
    }

    public Boolean hasUuid(String uuid){
        return state.containsKey(uuid);
    }
}
