package com.air.service;

import com.air.bean.Category;

import java.util.List;

public interface CategoryService {
    List<Category> selectCategoryList();

    Category selectCategoryByContentId(Long id);
}
