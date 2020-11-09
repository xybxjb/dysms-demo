package com.julings.consumer_demo.controller;


import com.julings.common.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user_demo")
    public User getUser(){
        return restTemplate.getForObject("http://user-module/user/12345",User.class);
    }

}
