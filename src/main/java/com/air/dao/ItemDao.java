package com.air.dao;


import com.air.bean.Item;
import com.air.common.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDao {
    List<Item> selectItems(@Param("itemName") String itemName, @Param("contentId") Integer contentId);

    List<Item> selectItemList(@Param("start") Integer start, @Param("rows") Integer rows, @Param("itemName") String itemName, @Param("contentId") Integer contentId);

    Integer selectItemListCount(@Param("itemName") String itemName, @Param("contentId") Integer contentId);
}