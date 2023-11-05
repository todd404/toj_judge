package com.todd.judger.judge;


import com.todd.judger.pojo.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StateMap {
    private static final Map<String, State> state = new ConcurrentHashMap<>();
    public void putState(String uuid, State s){
        state.put(uuid, s);
    }

    public State getState(String uuid){
        return state.get(uuid);
    }

    public Boolean hasUuid(String uuid){
        return state.containsKey(uuid);
    }
    public void deleteState(String uuid){ state.remove(uuid); }
}
