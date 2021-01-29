package com.example.demo.pojo.response;

public enum ResponseCode {
    Ok(200, "成功"),
    FAIL(400, "操作失败"),
    PARAMS_ERROR(401, "参数错误"),
    USER_NOT_EXIST_ERROR(402, "用户不存在"),
    ERROR(500,"系统错误");

    public int code;
    public String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
