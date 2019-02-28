package com.air.dao;


import com.air.bean.Item;
import com.air.common.utils.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemDao {
    List<Item> selectItemList(@Param("start") Integer start, @Param("size") Integer size, @Param("itemName") String itemName, @Param("subcategoryId") Integer subcategoryId);

    Integer selectItemListCount(@Param("itemName") String itemName, @Param("subcategoryId") Integer subcategoryId);

    Item selectItemById(@Param("id") Long id);

    Integer updateItemById(Item item);

    @Delete("delete from tb_item where id = #{id}")
    Integer deleteItemById(Long id);

    Item selectDetailedItemById(@Param("id") Long id);
}