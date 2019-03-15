package com.air.controller;

import com.air.bean.Menu;
import com.air.bean.ResultData;
import com.air.service.CategoryService;
import com.air.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    SubcategoryService subcategoryService;

    /**
     * 获取详细分类信息
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultData getDetailedCategoryList() {
        return new ResultData().success(categoryService.selectDetailedCategoryList());
    }

    /**
     * 获取一级分类或者二级分类列表
     *
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "choose", method = RequestMethod.GET)
    public ResultData listCategory(@RequestParam(defaultValue = "-1") int categoryId, HttpServletRequest request) {
        if (request.getParameter("menuName") != null) {
            Menu menu = Menu.getMenuByName(request.getParameter("menuName").trim());
            if (menu != null) {
                request.getSession().setAttribute("menu", menu);
            }
        }
        List dataList;
        if (categoryId < 0) {
            dataList = categoryService.selectCategoryList();
        } else {
            dataList = subcategoryService.selectSubcategoryListByCategoryId(categoryId);
        }
        return new ResultData().success(dataList);
    }

}
