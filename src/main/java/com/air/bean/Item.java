package com.air.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class Item{
    private Long id;

    private String name;

    private String title;

    private String image;

    private Long price;

    private String brand;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private String alias;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public List<ParamCategory> getParamCategoryList() {
        return paramCategoryList;
    }

    public void setParamCategoryList(List<ParamCategory> paramCategoryList) {
        this.paramCategoryList = paramCategoryList;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", created=" + created +
                ", alias='" + alias + '\'' +
                ", paramCategoryList=" + paramCategoryList +
                '}';
    }
}