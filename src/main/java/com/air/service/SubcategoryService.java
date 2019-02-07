package com.air.service;

import com.air.bean.Subcategory;

import java.util.List;

public interface SubcategoryService {
    List<Subcategory> selectSubcategoryListByCategoryId(Integer categoryId);

    Subcategory selectSubcategoryByItemId(Long id);
}
