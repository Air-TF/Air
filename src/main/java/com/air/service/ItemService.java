package com.air.service;

import com.air.bean.Item;
import com.air.common.utils.Page;

import java.util.List;

public interface ItemService {
    List<Item> selectItems(String name, Integer contentId);

    Page<Item> selectItemList(Integer page, Integer rows, String itemName, Integer contentId);
}
