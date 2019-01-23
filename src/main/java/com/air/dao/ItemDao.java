package com.air.dao;


import com.air.bean.Item;
import com.air.common.utils.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemDao {
    List<Item> selectItems(@Param("itemName") String itemName, @Param("contentId") Integer contentId);

    List<Item> selectItemList(@Param("start") Integer start, @Param("rows") Integer rows, @Param("itemName") String itemName, @Param("contentId") Integer contentId);

    Integer selectItemListCount(@Param("itemName") String itemName, @Param("contentId") Integer contentId);

    Item selectItemById(@Param("id") Long id);

    Integer updateItemById(Item item);

    @Delete("delete from tb_item where id = #{id}")
    Integer deleteItemById(Long id);
}