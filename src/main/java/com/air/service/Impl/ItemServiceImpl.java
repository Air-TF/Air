package com.air.service.Impl;

import com.air.bean.Item;
import com.air.common.utils.Page;
import com.air.dao.ItemDao;
import com.air.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;

    /**
     * 查询所有
     *
     * @param itemName
     * @param contentId
     * @return
     */
    @Override
    public List<Item> selectItems(String itemName, Integer contentId) {
        return itemDao.selectItems(itemName, contentId);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @param itemName
     * @param contentId
     * @return
     */
    @Override
    public Page<Item> selectItemList(Integer page, Integer rows, String itemName, Integer contentId) {
        Integer start = (page - 1) * rows;
        List<Item> itemList = itemDao.selectItemList(start, rows, itemName, contentId);
        Integer itemListCount = itemDao.selectItemListCount(itemName, contentId);
        Page<Item> itemPage = new Page<>();
        itemPage.setPage(page);
        itemPage.setRows(itemList);
        itemPage.setSize(rows);
        itemPage.setTotal(itemListCount);
        return itemPage;
    }

    /**
     * 通过Id查找产品
     * @param itemId
     * @return
     */
    @Override
    public Item selectItemById(Long itemId) {
        return itemDao.selectItemById(itemId);
    }
}
