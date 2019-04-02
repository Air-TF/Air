package com.air.dao;

import com.air.bean.Item;

public interface RecommendDao {
    Item getRecommendByHot(int categoryId);
}