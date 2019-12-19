package com.example.demo.controller;

import com.example.demo.controller.form.ValidationResult;
import com.example.demo.server.WebServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


/**
 * 父类Controller
 * @author zhanghao
 * @date 2019/7/29
 */
public class BaseController {
    protected Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    protected WebServer webServer;

    /**
     * 根据BindingResult生成简化的Result对象
     * @param result
     * @return
     */
    protected ValidationResult validate(BindingResult result) {
        StringBuilder builder = new StringBuilder();
        // 对象级错误
        if (result.hasGlobalErrors()) {
            for (ObjectError error : result.getGlobalErrors()) {
                builder.append(error.getDefaultMessage() + "; ");
            }
        }
        // 字段级错误
        if (result.hasFieldErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                if (error.isBindingFailure()) {
                    builder.append(error.getField() + ": " + "格式非法" + "; ");
                } else {
                    builder.append(error.getField() + ": " + error.getDefaultMessage() + "; ");
                }
            }
        }

        return new ValidationResult(builder.toString());
    }
}
