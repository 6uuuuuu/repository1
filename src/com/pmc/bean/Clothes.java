package com.pmc.bean;
/**
 * 商品clothes
 */

import java.io.Serializable;

public class Clothes implements Serializable {
    private String id;
    private String brand;//品牌
    private String color;//颜色
    private String size;//尺码
    private String style;//款式
    private String description;//描述
    private float price;//价格
    private int num;//库存

    public Clothes() {
    }

    public Clothes(String id, String brand, String color, String size, String style, String description, float price, int num) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.style = style;
        this.description = description;
        this.price = price;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
