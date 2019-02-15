package com.air.service;

import com.air.bean.Item;
import com.air.common.utils.Page;

import java.util.List;

public interface ItemService {
    Page<Item> selectItemList(Integer page, Integer rows, String itemName, Integer subcategoryId);

    Item selectItemById(Long itemId);

    Item selectDetailedItemById(Long id);

    Boolean updateItemById(Item item);

    boolean deleteItemById(Long id);
}
