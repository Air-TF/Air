package com.air.controller;

import com.air.bean.ResultData;
import com.air.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @RequestMapping("item/{id}")
    public ResultData listRecommendByItem(@PathVariable String id) {

        return new ResultData().success();
    }

    @RequestMapping("user/{id}")
    public ResultData listRecommendByUser(@PathVariable String id) {

        return new ResultData().success();
    }

    /**
     * 每个分类下最多浏览数商品
     * @param id
     * @return
     */
    @RequestMapping("category/{id}")
    public ResultData getRecommendByHot(@PathVariable String id) {
        return new ResultData().success(recommendService.getRecommendByHot(Integer.valueOf(id)));
    }
}
