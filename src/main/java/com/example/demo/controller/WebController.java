package com.example.demo.controller;

import com.example.demo.common.GetMessage;
import com.example.demo.common.MessageConsts;
import com.example.demo.controller.form.ValidationResult;
import com.example.demo.pojo.User;
import com.example.demo.response.ResultMsg;
import com.example.demo.server.WebServer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Web页面相关controller
 *
 * @author zhanghao
 */
@RestController
@RequestMapping(value = "/web")
public class WebController extends BaseController {
    @Autowired
    WebServer webServer;

    @ApiOperation(value = "获取用户信息", notes = "")
    @PostMapping(value = "/getUserInfo")
    public Object getUserInfo(@Valid @RequestBody User user){
        logger.error("错误");
//        logger.info("用户信息user" + user);
//        if ("123".equals(user.getName())){
//            throw new NoUserException("用户不存在");
//        }
//        if ("456".equals(user.getName())){
//            throw new NullPointerException();
//        }
//        ResultMsg resultMsg = new ResultMsg(200, "成功", null);

//        return resultMsg;
        return new ResultMsg(0,"",user);
    }

    @ApiOperation(value = "获取用户信息", notes = "")
    @PostMapping(value = "/testDemo")
    public Object testDemo(@Valid @Size(max = 5)String name, BindingResult result){
        logger.info("name"+name);
        // 提取表单校验结果
        ValidationResult vResult = validate(result);
        if (vResult.hasError()) {
            return new ResultMsg(0,"",vResult.getErrorMessage());
        }
        return new ResultMsg(0,"",null);
    }

    @PostMapping(value = "/testDemo2")
    public Object testDemo2(String name){
        logger.info("name"+name);
        return new ResultMsg(0,"",null);
    }
    @PostMapping(value = "/testDemo3")
    public Object testDemo3(){
        try {
            webServer.method();
        }catch (Exception e){
            System.out.println("");
        }

        return new ResultMsg(0,"",null);
    }
    
}
