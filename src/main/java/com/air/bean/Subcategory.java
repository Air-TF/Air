package com.air.bean;

import java.util.List;

public class Subcategory {
    private Long id;

    private String name;

    private List<ParamCategory> paramCategoryList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<ParamCategory> getParamCategoryList() {
        return paramCategoryList;
    }

    public void setParamCategoryList(List<ParamCategory> paramCategoryList) {
        this.paramCategoryList = paramCategoryList;
    }
}