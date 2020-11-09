package com.julings.user_module.controller;

import com.julings.common.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id){
        log.info("Enter param:id={}",id);
        log.info("Enter find user");

        return new User(id,"admin");
    }
}
