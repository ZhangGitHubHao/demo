package com.example.demo.response;

/**
 * 返回结果
 *
 * @author zhanghao
 */
public class ResultMsg {
    /**
     * 返回状态码
     */
    public Integer code;

    /**
     * 返回文言
     */
    public String msg;

    /**
     * 返回数据
     */
    public Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public ResultMsg(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultMsg() {
    }
}
