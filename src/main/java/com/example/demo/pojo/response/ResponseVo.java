package com.example.demo.pojo.response;

public class ResponseVo<T> {
    public int code;
    public String msg;
    public T data;

    private ResponseVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(ResponseCode.Ok.getCode(), ResponseCode.Ok.getMsg(), data);
    }

    public static <T> ResponseVo<T> success() {
        return success(null);
    }

    public static <T> ResponseVo<T> fail(T data) {
        return new ResponseVo<>(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg(), data);
    }

    public static <T> ResponseVo<T> fail() {
        return fail(null);
    }

    public static <T> ResponseVo<T> response(ResponseCode responseCode) {
        return response(responseCode, null);
    }

    public static <T> ResponseVo<T> response(ResponseCode responseCode, T data) {
        return new ResponseVo<>(responseCode.getCode(), responseCode.getMsg(), data);
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
