package com.air.bean;

import java.util.List;

public class ParamCategory {
    private Long id;

    private String name;

    private List<ParamSubcategory> paramSubcategoryList;

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

    public List<ParamSubcategory> getParamSubcategoryList() {
        return paramSubcategoryList;
    }

    public void setParamSubcategoryList(List<ParamSubcategory> paramSubcategoryList) {
        this.paramSubcategoryList = paramSubcategoryList;
    }
}