package com.air.service;

import com.air.bean.Category;

import java.util.List;

public interface CategoryService {
    List<Category> selectCategoryList();

    Category selectCategoryBySubcategoryId(Long id);

    Category selectCategoryByItemId(Long id);
}
