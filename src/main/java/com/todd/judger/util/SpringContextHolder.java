package com.todd.judger.util;

import com.todd.judger.judge.Judge.Judge;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder extends ApplicationObjectSupport {

    public Judge getJudge(String beanName){
        return super.getApplicationContext().getBean(beanName, Judge.class);
    }
}
