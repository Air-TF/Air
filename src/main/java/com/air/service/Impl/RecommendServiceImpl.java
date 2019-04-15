package com.air.service.Impl;

import com.air.bean.History;
import com.air.bean.Item;
import com.air.common.utils.RecommendUtils;
import com.air.dao.HistoryDao;
import com.air.dao.RecommendDao;
import com.air.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    RecommendDao recommendDao;
    @Autowired
    HistoryDao historyDao;

    @Override
    public List<Item> listRecommendByItem(Long id) {
        List<History> histories = historyDao.listHistoryByItem(id);
        List<Long> itemIdList = RecommendUtils.getUtils().ItemBased(histories, id, 12);
        return recommendDao.listRecommendByIds(itemIdList);
    }

    @Override
    public List<Item> listRecommendByUser(String id) {
        List<Item> itemList;
        List<History> histories = historyDao.listHistoryByUser(id);
        if (histories.size() == 0) {
            itemList = recommendDao.listRecommendByHot(6, 10);
        } else {
            List<Long> itemIdList = RecommendUtils.getUtils().UserBased(histories, id, 10);
            itemList = recommendDao.listRecommendByIds(itemIdList);
        }
        return itemList;
    }

    @Override
    public Item getRecommendByHot(Integer id) {
        return recommendDao.getRecommendByHot(id);
    }

    @Override
    public List<Item> listRecommendByHot() {
        return recommendDao.listRecommendByHot(0, 6);
    }
}
