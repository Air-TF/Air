package com.air.bean;

public class ParamSubcategory {
    private Long id;

    private String name;

    private Long paramCategoryId;

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

    public Long getParamCategoryId() {
        return paramCategoryId;
    }

    public void setParamCategoryId(Long paramCategoryId) {
        this.paramCategoryId = paramCategoryId;
    }
}