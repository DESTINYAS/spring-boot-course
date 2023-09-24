package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
//    Inject properties for coach.name and team.name
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

//    expose new endpoint "teamInfo"
    @GetMapping("teaminfo")
    public String getTeamInfo(){
        return  "Coach Name : "+coachName+", Team Name : "+teamName;
    }
//    Exposes a "/" that will return Hello World.
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }
//    expose a new end point for "workout"
    @GetMapping("/workout")
        public String getDailyWorkout(){
            return "Run a hard 5k!";
    }
//    Expose a new endpoint for fortune
    @GetMapping("/fortune")
    public String getDailyfortune(){
        return "Today is my lucky day";
    }
}
