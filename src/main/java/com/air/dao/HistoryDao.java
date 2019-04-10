package com.air.dao;

import com.air.bean.History;
import org.apache.ibatis.annotations.Param;

public interface HistoryDao {
    int insertHistory(History history);

    History selectHistory(@Param("itemId") Long itemId, @Param("userId") String userId);

    int updateHistory(History history);
}
