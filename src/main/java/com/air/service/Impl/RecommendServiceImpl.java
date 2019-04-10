package com.air.service.Impl;

import com.air.bean.Item;
import com.air.dao.RecommendDao;
import com.air.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    RecommendDao recommendDao;

    @Override
    public List<Item> listRecommendByItem(Integer id) {
        return recommendDao.listRecommendByItem(id);
    }

    @Override
    public List<Item> listRecommendByUser(String id) {
        return recommendDao.listRecommendByUser(id);
    }

    @Override
    public Item getRecommendByHot(Integer id) {
        return recommendDao.getRecommendByHot(id);
    }

    @Override
    public List<Item> listRecommendByHot() {
        return recommendDao.listRecommendByHot();
    }
}
