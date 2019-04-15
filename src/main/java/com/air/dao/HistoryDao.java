package com.air.dao;

import com.air.bean.History;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryDao {
    int insertHistory(History history);

    History selectHistory(@Param("itemId") Long itemId, @Param("userId") String userId);

    int updateHistory(History history);

    List<History> listHistoryByUser(@Param("userId") String userId);

    List<History> listHistoryByItem(@Param("itemId") Long itemId);

    void addHistory(History history);
}
