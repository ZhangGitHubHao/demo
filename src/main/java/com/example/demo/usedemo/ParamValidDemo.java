package com.example.demo.usedemo;

import com.example.demo.controller.BaseController;
import com.example.demo.controller.form.UserForParamValidForm;
import com.example.demo.controller.form.ValidationResult;
import com.example.demo.pojo.User;
import com.example.demo.response.ResultMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * 参数校验使用Demo
 *
 * @author zhanghao
 */
@RestController
@RequestMapping(value = "/demo")
public class ParamValidDemo extends BaseController {

    @ApiOperation(value = "获取用户信息", notes = "")
    @PostMapping(value = "/paramValidDemo")
    public Object testDemo(@Valid @RequestParam("name")String name, BindingResult result){
        logger.info("name"+name);
        // 提取表单校验结果
        ValidationResult vResult = validate(result);
        if (vResult.hasError()) {
            return new ResultMsg(0,"",vResult.getErrorMessage());
        }
        return new ResultMsg(0,"",null);
    }

}