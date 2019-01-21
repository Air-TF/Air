package com.air.dao;

import com.air.bean.Content;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContentDao {

    @Select("select * from tb_content where category_id = #{categoryId}")
    List<Content> selectContentByCategoryId(Integer categoryId);
}