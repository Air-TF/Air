package com.air.service;

import com.air.bean.Content;

import java.util.List;

public interface ContentService {
    List<Content> selectContentByCategoryId(Integer categoryId);
}
