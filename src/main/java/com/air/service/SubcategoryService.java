package com.air.service;

import com.air.bean.Subcategory;

import java.util.List;

public interface SubcategoryService {
    List<Subcategory> selectSubcategoryByCategoryId(Integer categoryId);
}
