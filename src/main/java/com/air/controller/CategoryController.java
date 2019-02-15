package com.air.controller;

import com.air.bean.ResultData;
import com.air.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public ResultData getDetailedCategoryList() {
        return new ResultData().success(categoryService.selectDetailedCategoryList());
    }
}
