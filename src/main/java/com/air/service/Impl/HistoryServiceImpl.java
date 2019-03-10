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
}
