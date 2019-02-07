package com.air.service.Impl;

import com.air.bean.Subcategory;
import com.air.dao.SubcategoryDao;
import com.air.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {
    @Autowired
    SubcategoryDao subcategoryDao;

    @Override
    public List<Subcategory> selectSubcategoryListByCategoryId(Integer categoryId) {
        return subcategoryDao.selectSubcategoryListByCategoryId(categoryId);
    }

    @Override
    public Subcategory selectSubcategoryByItemId(Long id) {
        return subcategoryDao.selectSubcategoryByItemId(id);
    }
}
