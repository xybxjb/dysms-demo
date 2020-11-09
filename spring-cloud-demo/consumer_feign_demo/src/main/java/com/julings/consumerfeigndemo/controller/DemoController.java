package com.julings.consumerfeigndemo.controller;

import com.julings.common.pojo.User;
import com.julings.consumerfeigndemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    public UserService userService;

    @GetMapping("/user_demo")
    public User getUser(){
        return userService.getUser(12345L);
    }

}
