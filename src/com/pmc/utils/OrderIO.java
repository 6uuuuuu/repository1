package com.pmc.utils;

import com.pmc.bean.Order;
import com.pmc.bean.OrderItems;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 进行订单相关的操作，存入order.obj文件中
 */
public class OrderIO {
    private static List<Order> orders = new ArrayList<>();
    private static final String ORDER_FILE = "order.obj";
    //写入订单信息
    public void writeOrders(Order order) throws BusinessException{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDER_FILE));
            oos.writeObject(orders);
            oos.close();
        } catch (IOException e) {
            throw new BusinessException("io.order.write.error");
        }
    }

    //读取订单信息
    public void readOrders() throws BusinessException{
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDER_FILE));
            orders = (List<Order>) ois.readObject();//orders为类的静态变量，因此该方法不需要返回一个List<Order>的值
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            writeOrders(new Order());
            throw new BusinessException("io.order.read.error");
        }
    }

    //添加订单信息
    public void addOrders(Order order) {
        order.setOrderId(orders.size() + 1);//设置订单Id每次加一
        orders.add(order);//每次新生成一个订单后，将该订单的信息加入orders这个List中
    }

    /**
     * 根据用户查找其名下的订单
     * @param userId
     * @return
     */
    public List<Order> findOrdersbyUserId(int userId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getUserId() == userId) {
                userOrders.add(o);
            }
        }
        return userOrders;
    }

    /**
     * 根据订单Id查找唯一的订单
     * @param orderId
     * @return
     */
    public Order findOrderbyOrderId(int orderId) {
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
               return o;
            }
        }
        return null;
    }

    /**
     * 用于管理员查看全部订单信息
     */
    public static void printAllOrders() {
        for (Order o : orders) {
            System.out.println(o.toString());
            for (OrderItems items:o.getOrderItemsList() ) {
                System.out.println(items.toString());
                System.out.println(items.getClothes().toString());
            }
        }
    }

}
