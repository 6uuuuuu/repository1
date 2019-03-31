package com.pmc.service;

import com.pmc.bean.Order;
import com.pmc.utills.BusinessException;

import java.util.List;

/**
 * 对订单进行操作的接口
 */
public interface OrderService {
    public void addOrder() throws BusinessException;
    public List<Order> findOwnOrders() throws BusinessException;
    public Order findOrderbySth() throws BusinessException;

}
