package com.pmc.service;

import com.pmc.bean.Order;
import com.pmc.utils.BusinessException;

import java.util.List;

/**
 * 对订单进行操作的接口
 */
public interface OrderService {
    public List<Order> findOwnOrders(int userId) throws BusinessException;
    public Order findOrderbySth(int orderId) throws BusinessException;
    public void creatOrder(Order order) throws BusinessException;

}
