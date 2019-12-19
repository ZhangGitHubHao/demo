package com.example.demo.controller.form;

import javax.validation.constraints.NotNull;

/**
 * @author zhangh
 */
public class UserForParamValidForm {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserForParamValidForm{" +
                "name='" + name + '\'' +
                '}';
    }
}
