package com.pmc.bean;
/**
 * 订单明细
 */

import java.io.Serializable;

public class OrderItems implements Serializable {
    private String itemId;//订单id
    private Clothes clothes;//购买的商品
    private int shoppingNum;//购买的数量
    private float sum;//购买的金额

    public OrderItems(String itemId, Clothes clothes, int shoppingNum, float sum) {
        this.itemId = itemId;
        this.clothes = clothes;
        this.shoppingNum = shoppingNum;
        this.sum = sum;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public int getShoppingNum() {
        return shoppingNum;
    }

    public void setShoppingNum(int shoppingNum) {
        this.shoppingNum = shoppingNum;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "itemId='" + itemId + '\'' +
                ", shoppingNum=" + shoppingNum +
                ", sum=" + sum +
                '}';
    }
}
