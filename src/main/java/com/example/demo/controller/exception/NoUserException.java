package com.example.demo.controller.exception;

/**
 * 用户不存在异常
 *
 * @author zhanghao
 */
public class NoUserException extends RuntimeException {
    private Integer code = 301;

    private String msg;

    public NoUserException (){}

    public NoUserException(String msg){
        super(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
