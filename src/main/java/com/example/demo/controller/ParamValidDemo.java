package com.example.demo.controller;

import com.example.demo.controller.BaseController;
import com.example.demo.controller.form.UserForParamValidForm;
import com.example.demo.controller.form.ValidationResult;
import com.example.demo.pojo.response.ResultMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 参数校验使用Demo
 *
 * @author zhanghao
 */
@RestController
@RequestMapping(value = "/demo")
//@Validated
public class ParamValidDemo extends BaseController {

    @ApiOperation(value = "获取用户信息", notes = "")
    @PostMapping(value = "/paramValidDemo")
    public Object testDemo(@Valid @NotBlank @RequestParam("name")String name, BindingResult result){
        logger.info("name"+name);
        // 提取表单校验结果
        ValidationResult vResult = validate(result);
        if (vResult.hasError()) {
            return new ResultMsg(0,"",vResult.getErrorMessage());
        }
        return new ResultMsg(0,"",null);
    }

    @PostMapping(value = "/paramValidDemo1")
    public Object testDemo1(@Valid @RequestBody UserForParamValidForm user){
        System.out.println("通过");
        return new ResultMsg(0,"",null);
    }

    /**
     * 单个参数时 Valid加在方法参数上不能校验
     * @param name
     * @return
     */
    @PostMapping(value = "/paramValidDemo2")
    public Object testDemo2(@Valid @NotBlank @RequestParam("name") String name){
        System.out.println("通过");
        return new ResultMsg(0,"",null);
    }

    /**
     * 单个参数校验将Validated加在类上
     * @param name
     * @return
     */
    @PostMapping(value = "/paramValidDemo3")
    public Object testDemo3(@NotBlank @RequestParam("name") String name){
        System.out.println("通过");
        return new ResultMsg(0,"",null);
    }

    /**
     * Validated加在方法参数上不能校验
     * @param name
     * @return
     */
    @PostMapping(value = "/paramValidDemo4")
    public Object testDemo4(@Validated @NotBlank @RequestParam("name") String name){
        System.out.println("通过");
        return new ResultMsg(0,"",null);
    }

    /**
     * Validated加在方法上不能进行校验
     * @param name
     * @return
     */
    @Validated
    @PostMapping(value = "/paramValidDemo5")
    public Object testDemo5(@NotBlank @RequestParam("name") String name){
        System.out.println("通过");
        return new ResultMsg(0,"",null);
    }

}