package com.jiaxinyu.dao;

import com.jiaxinyu.domain.User;

import java.util.List;

public interface UserDao{
//    @Select("select * from user")
    List<User> findAll();
}