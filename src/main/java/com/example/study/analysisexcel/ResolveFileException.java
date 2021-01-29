package com.example.study.analysisexcel;

/**
 * 解析Excel自定义异常类。
 * @author zhangh
 * @date 2019/10/21
 */
public class ResolveFileException extends RuntimeException{
    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorMessage;

    public ResolveFileException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ResolveFileException(String message) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
