package com.air.controller;

import com.air.bean.Item;
import com.air.bean.ResultData;
import com.air.common.utils.Page;
import com.air.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResultData getDetailItem(@PathVariable String id) {
        Item item = itemService.selectDetailedItemById(Long.valueOf(id));
        return new ResultData().success(item);
    }

    @RequestMapping(value = "newItem", method = RequestMethod.POST)
    public ResultData addNewItem(@RequestBody Item item) {
        return new ResultData().success();
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResultData getItemList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                  String keyword,Integer subcategoryId) {
        Page<Item> itemPage = itemService.selectItemList(page, size, keyword, subcategoryId);
        return new ResultData().success(itemPage);
    }

}