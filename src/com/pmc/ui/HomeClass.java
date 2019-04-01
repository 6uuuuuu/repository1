package com.pmc.ui;

import com.pmc.bean.Clothes;
import com.pmc.bean.Order;
import com.pmc.bean.OrderItems;
import com.pmc.service.ClothesService;
import com.pmc.service.OrderService;
import com.pmc.service.impl.ClothesServiceImpl;
import com.pmc.service.impl.OrderServiceImpl;
import com.pmc.utils.BusinessException;
import com.pmc.utils.ConsoleTable;
import com.pmc.utils.DateUtils;
import com.pmc.utils.OrderIO;

import java.util.Date;
import java.util.List;

/**
 * 登陆后进入的主界面，展示商品
 */
public class HomeClass extends BaseClass {
    ClothesService clothesService = new ClothesServiceImpl();
     OrderService orderService= new OrderServiceImpl();
    OrderIO orderIO = new OrderIO();
    public void show() {
        showClothesList();//展示商品
       try {
            orderIO.readOrders();
        } catch (BusinessException e) {
            println(getString(e.getMessage()));
        }
        boolean flag = true;
        while (flag) {
            println(getString("home.function"));//1、查询全部订单 2、查找订单 3、购买 0、退出
            println(getString("info.select"));//请选择
            String s = input.nextLine();//读取用户输入的内容
            switch (s) {
                case "1":
                    try {
                        findAllOrders();
                    } catch (BusinessException e) {
                        println(getString(e.getMessage()));
                    }
                    break;
                case "2":
                    try {
                        findOrder();
                    } catch (BusinessException e) {
                        println(getString(e.getMessage()));
                    }
                    break;
                case "3":
                    try {
                        buyProducts();
                    } catch (BusinessException e) {
                        println(getString(e.getMessage()));
                    }
                    break;
                case "0":
                    flag = false;
                    System.exit(0);
                    break;
                default:
                    println(getString("input.error"));//输入错误则继续读取直到用户输入正确的信息
                    break;
            }
        }
        if (currentUser.getUsername().equals(getString("system.admin"))) {
            println(getString("system.order.message"));
            String s = input.nextLine();
            if("Y".equals(s)) {
                orderIO.printAllOrders();
            }
        }
    }

    private void buyProducts() throws BusinessException {
        int count = 0;
        boolean flag = true;
        Order order = new Order();
        float sum = 0.0f;
        while (flag) {
            //生成订单明细
            println(getString("buy.id"));
            String buyId = input.nextLine();
            println(getString("buy.num"));
            String buyNumStr = input.nextLine();
            int buyNum = 0;
            try {
                buyNum = Integer.parseInt(buyNumStr);
            } catch (NumberFormatException e) {
                throw new BusinessException("input.error");
            }
            Clothes buyClothes = clothesService.findClothesbyClothesId(buyId, buyNum);
            if (buyClothes == null) {
                throw new BusinessException("products.notexist");
            }
            //订单明细
            OrderItems orderItems = new OrderItems("item-" + (++count),
                    buyClothes, buyNum, buyClothes.getPrice() * buyNum);
            order.getOrderItemsList().add(orderItems);//将明细加入订单中
            sum = sum +orderItems.getSum();//订单总额为明细之和
            println(getString("buy.continue"));//是否继续添加商品
            String s = input.nextLine();
            if("Y".equals(s)){
                flag = true;//继续购买
            }else{
                flag = false;//退出生成订单明细的过程
            }
        }
        //生成一个完整的订单
        order.setSum(sum);
        order.setUserId(currentUser.getId());
        order.setCreatDate(DateUtils.toDate(new Date()));//获取当前时间 System.currentTimeMillis()
        orderIO.addOrders(order);//为orders添加订单信息
        orderService.creatOrder(order);//将订单信息存入obj中
    }

    private void findOrder() throws BusinessException{
        println(getString("order.id"));
        String s = input.nextLine();
        int orderId = 0;
        try {
            orderId = Integer.parseInt(s);
            orderService.findOrderbySth(orderId);
        } catch (NumberFormatException e) {
            throw new BusinessException("input.error");
        }
    }

    private void findAllOrders() throws BusinessException{
        orderService.findOwnOrders(currentUser.getId());
    }

    private void showClothesList() {
        List<Clothes> clothesList = clothesService.getClothesList();
        ConsoleTable table = new ConsoleTable(8, true);//8列，展示表头
        table.appendRow();//增加一行
        table.appendColum("id")
                .appendColum("brand")
                .appendColum("color")
                .appendColum("size")
                .appendColum("style")
                .appendColum("description")
                .appendColum("price")
                .appendColum("num");
        for (Clothes clothes : clothesList) {
            table.appendRow();//增加一行
            table.appendColum(clothes.getId())
                    .appendColum(clothes.getBrand())
                    .appendColum(clothes.getColor())
                    .appendColum(clothes.getSize())
                    .appendColum(clothes.getStyle())
                    .appendColum(clothes.getDescription())
                    .appendColum(clothes.getPrice())
                    .appendColum(clothes.getNum());
        }
        System.out.println(table.toString());
    }
}
