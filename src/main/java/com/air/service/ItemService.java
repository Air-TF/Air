package com.air.service;

import com.air.bean.Item;
import com.air.bean.Page;

public interface ItemService {
    Page<Item> selectItemList(Integer page, Integer size, String keyword, Integer subcategoryId);

    Item selectItemById(Long itemId);

    Item selectDetailedItemById(Long id);

    Boolean updateItemById(Item item);

    boolean deleteItemById(Long id);
}
