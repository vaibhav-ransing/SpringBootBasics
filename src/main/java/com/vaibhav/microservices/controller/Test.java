package com.vaibhav.microservices.controller;

import com.vaibhav.microservices.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController  // combination of @ResponseBody and @Controller
public class Test {

    Logger logger = LoggerFactory.getLogger(Test.class);


    @GetMapping("/")  // RequestMapping("/")
    public String home() {
        return "Vaibhav";
    }

    @GetMapping("/user")
    public User user(){
        User user = new User();
        user.setEmailId("vaibahv@gmail.com");
        user.setId("41");
        user.setName("Vaibhav Ransing");
        temp();
        return user;
    }

    public void temp(){
        System.out.println("Hello");
    }

    @GetMapping("/user/{id}/{id2}")
    public String userById(@PathVariable String id, @PathVariable("id2") String name){
        return "user id " + id +" "+ name;
    }
}