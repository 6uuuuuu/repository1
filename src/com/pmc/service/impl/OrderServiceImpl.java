package com.pmc.service.impl;

import com.pmc.bean.Order;
import com.pmc.bean.OrderItems;
import com.pmc.service.OrderService;
import com.pmc.utils.BusinessException;
import com.pmc.utils.OrderIO;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderIO orderIO = new OrderIO();

    @Override
    public List<Order> findOwnOrders(int userId) throws BusinessException {
        List<Order> ords = orderIO.findOrdersbyUserId(userId);
        printOrders4User(ords);
        return ords;
    }

    @Override
    public Order findOrderbySth(int orderId) throws BusinessException {
        Order o=orderIO.findOrderbyOrderId(orderId);
        System.out.println(o.toString());
        for (OrderItems items:o.getOrderItemsList() ) {
            System.out.println(items.toString());
            System.out.println(items.getClothes().toString());
        }
        return o;
    }

    @Override
    public void creatOrder(Order order) throws BusinessException {
        orderIO.writeOrders(order);
    }

    public void printOrders4User(List<Order> ords) {
        for (Order o : ords) {
            System.out.println(o.toString());
            for (OrderItems items:o.getOrderItemsList() ) {
                System.out.println(items.toString());
                System.out.println(items.getClothes().toString());
            }
        }
    }
}
