package com.pmc.bean;

import com.pmc.utills.OrderStatusType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private int OrderId;
    private List<OrderItems> orderItemsList = new ArrayList<>();
    private float sum;//
    private int userId;//
    private String creatDate;//
    private OrderStatusType status = OrderStatusType.UNPAID;//状态为未付款

    public Order() {
    }

    public Order(int orderId, List<OrderItems> orderItemsList, float sum, int userId, String creatDate, OrderStatusType status) {
        OrderId = orderId;
        this.orderItemsList = orderItemsList;
        this.sum = sum;
        this.userId = userId;
        this.creatDate = creatDate;
        this.status = status;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }
}
