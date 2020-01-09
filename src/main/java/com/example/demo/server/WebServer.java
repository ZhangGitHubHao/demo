package com.example.demo.server;


import com.example.demo.pojo.User;

public interface WebServer {
    void method();

    // redis 缓存测试
    User redisTest();
}
