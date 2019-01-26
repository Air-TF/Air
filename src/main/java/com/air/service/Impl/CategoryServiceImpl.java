package com.air.service.Impl;

import com.air.bean.Category;
import com.air.dao.CategoryDao;
import com.air.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> selectCategoryList() {
        return categoryDao.selectCategoryList();
    }

    @Override
    public Category selectCategoryBySubcategoryId(Long id) {
        return categoryDao.selectCategoryBySubcategoryId(id);
    }
}
