package com.example.demo.server.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.server.WebServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * WebServer实现类
 * @author zhanghao
 * @date 2019/7/29
 */
@Service
public class WebServerImpl implements WebServer {
    @Autowired
    UserMapper userMapper;
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void method() throws NullPointerException{
        User user = new User("wang",12,"男");
        System.out.println("45646");
        userMapper.insert(user);
//        throw new  NullPointerException("12313");
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
