package com.air.service;

import com.air.bean.History;

public interface HistoryService {
    Boolean insertHistory(History history);

    History getHistory(Long itemId, String userId);

    boolean updateHistory(History history);
}
