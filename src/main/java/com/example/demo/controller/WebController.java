package com.example.demo.controller;

import com.example.demo.controller.form.ValidationResult;
import com.example.demo.pojo.User;
import com.example.demo.response.ResultMsg;
import com.example.demo.server.WebServer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Web页面相关controller
 *
 * @author zhanghao
 */
@RestController
@RequestMapping(value = "/web")
public class WebController extends BaseController {
    @Autowired
    WebServer webServer;

    @ApiOperation(value = "获取用户信息", notes = "")
    @PostMapping(value = "/getUserInfo")
    public Object getUserInfo(@Valid @RequestBody User user) {
        logger.error("错误");
//        logger.info("用户信息user" + user);
//        if ("123".equals(user.getName())){
//            throw new NoUserException("用户不存在");
//        }
//        if ("456".equals(user.getName())){
//            throw new NullPointerException();
//        }
//        ResultMsg resultMsg = new ResultMsg(200, "成功", null);

//        return resultMsg;
        return new ResultMsg(0, "", user);
    }

    @ApiOperation(value = "获取用户信息", notes = "")
    @PostMapping(value = "/testDemo")
    public Object testDemo(@Valid @Size(max = 5) String name, BindingResult result) {
        logger.info("name" + name);
        // 提取表单校验结果
        ValidationResult vResult = validate(result);
        if (vResult.hasError()) {
            return new ResultMsg(0, "", vResult.getErrorMessage());
        }
        return new ResultMsg(0, "", null);
    }

    @PostMapping(value = "/testDemo2")
    public Object testDemo2(String name) {
        logger.info("name" + name);
        return new ResultMsg(0, "", null);
    }

    @PostMapping(value = "/testDemo3")
    public Object testDemo3() {
        try {
            webServer.method();
        } catch (Exception e) {
            System.out.println("");
        }

        return new ResultMsg(0, "", null);
    }

    /**
     * redis 缓存测试
     *
     * @return
     */
    @PostMapping(value = "/testDemo4")
    public Object testDemo4() {
        return new ResultMsg(0, "", webServer.redisTest());
    }

    /**
     * 文件下载测试
     *
     * @return
     */
    @PostMapping(value = "testDemo5")
    public Object testDemo5(HttpServletResponse response) {
        String path = "C:\\Users\\860118060\\Desktop\\TODO.txt";
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ResultMsg(0, "", null);
    }
}
