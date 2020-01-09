package com.example.demo.server.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.server.WebServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * WebServer实现类
 * @author zhanghao
 * @date 2019/7/29
 */
@Service
@CacheConfig(cacheNames = "cacheTest")
public class WebServerImpl implements WebServer {
    @Autowired
    UserMapper userMapper;
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void method() throws NullPointerException{
        User user = new User();
        user.setName("张三");
        user.setAge(12);
        user.setGender("男");
        userMapper.insert(user);
    }

    @Cacheable(key = "'td2'")
    @Override
    public User redisTest() {
        User user = new User();
        user.setName("星期二");
        user.setAge(24);
        user.setGender("女");
        return user;
    }


}
