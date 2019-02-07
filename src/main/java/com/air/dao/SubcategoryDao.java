package com.air.dao;

import com.air.bean.Subcategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SubcategoryDao {

    @Select("select * from tb_subcategory where category_id = #{categoryId}")
    List<Subcategory> selectSubcategoryListByCategoryId(Integer categoryId);

    Subcategory selectSubcategoryByItemId(@Param("id") Long id);
}