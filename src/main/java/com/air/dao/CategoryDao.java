package com.air.dao;

import com.air.bean.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryDao {

    @Select("select * from tb_category")
    List<Category> selectCategoryList();

    @Select("select * from tb_category where id = #{id}")
    Category selectCategoryByContentId(@Param("id") Long id);
}