package com.example.demo.exception;

import com.example.demo.response.ResultMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author zh
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(NoUserException.class)
    public Object handleException(NoUserException e) {
        return new ResultMsg(301, "用户不存在!", null);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        System.out.println(e);
        return new ResultMsg(500, "未知异常!!!", null);
    }
}
