package com.air.service;

import com.air.bean.Item;

import java.util.List;


public interface RecommendService {
    Item getRecommendByHot(Integer id);
    List<Item> listRecommendByItem(Integer id);
    List<Item> listRecommendByUser(String id);
    List<Item> listRecommendByHot();
}
