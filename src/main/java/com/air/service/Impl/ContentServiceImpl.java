package com.air.service.Impl;

import com.air.bean.Content;
import com.air.dao.ContentDao;
import com.air.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentDao contentDao;

    @Override
    public List<Content> selectContentByCategoryId(Integer categoryId) {
        return contentDao.selectContentByCategoryId(categoryId);
    }
}
