package com.love2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{
    public CricketCoach(){
        System.out.println("In constructor "+ getClass().getSimpleName());
    }
//    Define our init method
    @PostConstruct
    public void doMyStartUpStuffs(){
        System.out.println("In doMyStartUpStuffs "+ getClass().getSimpleName());
    }

//    Define our destroy method
    @PreDestroy
    public void doMyCleanUpStuffs(){
        System.out.println("In doMyCleanUpStuffs "+ getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
