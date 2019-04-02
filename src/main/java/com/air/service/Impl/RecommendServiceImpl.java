package com.air.service.Impl;

import com.air.bean.Item;
import com.air.dao.RecommendDao;
import com.air.dao.SubcategoryDao;
import com.air.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    SubcategoryDao subcategoryDao;
    @Autowired
    RecommendDao recommendDao;

    @Override
    public Item getRecommendByHot(Integer id) {
        return recommendDao.getRecommendByHot(id);
    }
}
