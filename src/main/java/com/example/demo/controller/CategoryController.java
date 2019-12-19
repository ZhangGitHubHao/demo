package com.example.demo.controller;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.pojo.Category;
import com.example.demo.response.ResultMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController extends BaseController{
    @Autowired
    CategoryDAO categoryDAO;

    @ApiOperation(value = "分页列表", notes = "")
    @RequestMapping(value = "/listCategory",method = RequestMethod.GET)
    public ResultMsg listCategory(@RequestParam("start") Integer start,@RequestParam("size") Integer size){
        start = start<0?0:start;
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(start-1, size, sort);
        Page<Category> page =categoryDAO.findAll(pageable);

        return new ResultMsg(200,"成功",page.getContent());
    }
}
