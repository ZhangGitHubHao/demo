package com.example.demo.controller.exception;

import com.example.demo.pojo.response.ResponseCode;
import com.example.demo.pojo.response.ResponseVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局异常处理
 *
 * @author zh
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(NoUserException.class)
    public Object handleException(NoUserException e) {
        return ResponseVo.response(ResponseCode.USER_NOT_EXIST_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error(e);
        return ResponseVo.response(ResponseCode.ERROR);
    }

    /**
     * 数据绑定异常处理
     * @param request 请求信息
     * @param exception 异常信息
     * @return 请求参数错误
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ResponseVo handleBindException(HttpServletRequest request, Exception exception) {
        BindingResult result;
        if (exception instanceof BindException) {
            System.out.println("BindException");
            result = ((BindException) exception).getBindingResult();
        } else {
            System.out.println("MethodArgumentNotValidException");
            result = ((MethodArgumentNotValidException)exception).getBindingResult();
        }

        StringBuilder builder = new StringBuilder();
        // 如果存在错误
        if (result.hasErrors()) {
            // 往错误消息中添加对象级错误
            for (ObjectError error : result.getGlobalErrors()) {
                String errorMessage = String.format("%s: %s; ", error.getObjectName(), error.getDefaultMessage());
                builder.append(errorMessage);
            }
            // 往错误消息中添加字段级错误
            for (FieldError error : result.getFieldErrors()) {
                String errorMessage;
                if (error.isBindingFailure()) {
                    // 数据绑定失败，默认信息会暴露太多细节，替换为自定义信息
                    errorMessage = String.format("%s: 非法数据; ", error.getField());
                } else {
                    // 数据校验不通过，返回默认信息
                    errorMessage = String.format("%s: %s; ", error.getField(), error.getDefaultMessage());
                }
                builder.append(errorMessage);
            }
        }

        log.info(String.format("拒绝访问。非法参数: %s -> %s", request.getServletPath(), builder.toString()));

        return ResponseVo.response(ResponseCode.PARAMS_ERROR);
    }

//    /**
//     * 数据绑定异常处理 (form参数)
//     * @param result 绑定结果
//     * @return 统一响应对象
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseVo handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,HttpServletRequest request) {
//        BindingResult result = exception.getBindingResult();
//        StringBuilder builder = new StringBuilder();
//        // 如果存在错误
//        if (result.hasErrors()) {
//            // 往错误消息中添加对象级错误
//            for (ObjectError error : result.getGlobalErrors()) {
//                String errorMessage = String.format("%s: %s; ", error.getObjectName(), error.getDefaultMessage());
//                builder.append(errorMessage);
//            }
//            // 往错误消息中添加字段级错误
//            for (FieldError error : result.getFieldErrors()) {
//                String errorMessage;
//                if (error.isBindingFailure()) {
//                    // 数据绑定失败，默认信息会暴露太多细节，替换为自定义信息
//                    errorMessage = String.format("%s: 非法数据; ", error.getField());
//                } else {
//                    // 数据校验不通过，返回默认信息
//                    errorMessage = String.format("%s: %s; ", error.getField(), error.getDefaultMessage());
//                }
//                builder.append(errorMessage);
//            }
//        }
//
//        log.info(String.format("拒绝访问。非法参数: %s -> %s", request.getServletPath(), builder.toString()));
//
//        return ResponseVo.response(ResponseCode.PARAMS_ERROR);
//    }

    /**
     * 单个参数校验不通过异常
     *
     * @param result 绑定结果
     * @return 统一响应对象
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseVo constraintViolationException(ConstraintViolationException ex) {
        // 获取具体的错误信息
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        // 打印数据
        violations.forEach(e -> System.out.println(e.getMessage()));

        return ResponseVo.response(ResponseCode.PARAMS_ERROR);
    }
}
