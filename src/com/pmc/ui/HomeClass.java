package com.pmc.ui;

import com.pmc.bean.Clothes;
import com.pmc.bean.Order;
import com.pmc.bean.OrderItems;
import com.pmc.bean.User;
import com.pmc.service.ClothesService;
import com.pmc.service.impl.ClothesServiceImpl;
import com.pmc.utils.BusinessException;
import com.pmc.utils.ConsoleTable;
import com.pmc.utils.OrderIO;

import java.util.List;

/**
 * 登陆后进入的主界面，展示商品
 */
public class HomeClass extends BaseClass {
    ClothesService clothesService = new ClothesServiceImpl();
    OrderIO orderIO = new OrderIO();
    public void show() {
        showClothesList();//展示商品
        orderIO.readOrders();//读取已存在的订单信息，初始化orders
        boolean flag = true;
        while (flag) {
            println(getString("home.function"));//1、查询全部订单 2、查找订单 3、购买 0、退出
            println(getString("info.select"));//请选择
            String s = input.nextLine();//读取用户输入的内容
            switch (s) {
                case "1":
                    try {
                        findAllOrders();
                        flag = false;
                    } catch (BusinessException e) {
                        println(getString(e.getMessage()));
                    }
                    break;
                case "2":
                    try {
                        findOrder();
                        flag = false;
                    } catch (BusinessException e) {
                        println(getString(e.getMessage()));
                    }
                    break;
                case "3":
                    try {
                        buyProducts(currentUser);
                        flag = false;
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
                OrderIO.printAllOrders();
            }
        }
    }

    private void buyProducts(User currentUser) throws BusinessException {
        int count = 0;
        boolean flag = true;
        Order order = new Order();
        while (flag) {
            //生成订单明细
            println(getString("buy.id"));
            String buyId = input.nextLine();
            println(getString("buy.num"));
            int buyNum = input.nextInt();
            Clothes buyclothes = clothesService.findClothesbyClothesId(buyId, buyNum);
            if (buyclothes == null) {
                throw new BusinessException("products.notexist");
            }
            //订单明细
            OrderItems orderItems = new OrderItems("item-" + (++count),
                    buyclothes, buyNum, buyclothes.getPrice() * buyNum);
            order.getOrderItemsList().add(orderItems);//将明细加入订单中
            println(getString("buy.continue"));//是否继续添加商品
            String s = input.nextLine();
            if(!"Y".equals(s)){
                flag = false;//退出生成订单明细的过程
            }
        }
        //生成一个完整的订单
        orderIO.addOrders(order);
    }

    private void findOrder() {
    }

    private void findAllOrders() {

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
