package com.air.dao;

import com.air.bean.Item;

import java.util.List;

public interface RecommendDao {

    List<Item> listRecommendByItem(Integer id);

    List<Item> listRecommendByUser(String id);

    Item getRecommendByHot(int categoryId);

    List<Item> listRecommendByHot();
}