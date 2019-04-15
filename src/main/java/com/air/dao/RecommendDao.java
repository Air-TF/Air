package com.air.dao;

import com.air.bean.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendDao {

    List<Item> listRecommendByIds(@Param("itemIdList") List<Long> itemIdList);

    Item getRecommendByHot(int categoryId);

    List<Item> listRecommendByHot(@Param("start") Integer start,@Param("num") Integer num);
}