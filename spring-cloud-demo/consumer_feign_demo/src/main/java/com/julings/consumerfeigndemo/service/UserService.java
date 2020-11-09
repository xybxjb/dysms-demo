package com.julings.consumerfeigndemo.service;

import com.julings.common.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value="user-module")
public interface UserService {

    @GetMapping(value = "/user/{id}")
    User getUser(@PathVariable("id") Long id);

}
