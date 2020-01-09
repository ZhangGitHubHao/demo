package com.example.demo.dao;

import com.example.demo.pojo.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}