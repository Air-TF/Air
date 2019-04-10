package com.air.service.Impl;

import com.air.bean.History;
import com.air.dao.HistoryDao;
import com.air.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryDao historyDao;

    @Override
    public Boolean insertHistory(History history) {
        return historyDao.insertHistory(history) == 1 ? true : false;
    }

    @Override
    public History getHistory(Long itemId, String userId) {
        return historyDao.selectHistory(itemId,userId);
    }

    @Override
    public boolean updateHistory(History history) {
        return  historyDao.updateHistory(history) == 1 ? true : false;
    }
}
