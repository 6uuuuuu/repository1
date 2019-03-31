package com.pmc.ui;

import com.pmc.bean.Clothes;
import com.pmc.bean.Order;
import com.pmc.bean.OrderItems;
import com.pmc.bean.User;
import com.pmc.service.ClothesService;
import com.pmc.service.impl.ClothesServiceImpl;
import com.pmc.utills.BusinessException;
import com.pmc.utills.ConsoleTable;
import com.pmc.utills.OrderIO;

import java.util.List;

/**
 * 登陆后进入的主界面，展示商品
 */
public class HomeClass extends BaseClass {
    ClothesService clothesService = new ClothesServiceImpl();

    public void show() {
        showClothesList();//展示商品
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
    }

    private void buyProducts(User currentUser) throws BusinessException {
        int count = 0;
        boolean flag = true;
        while (flag) {
            //生成订单
            println(getString("buy.id"));
            String buyId = input.nextLine();
            println(getString("buy.num"));
            int buyNum = input.nextInt();
            Clothes buyclothes = clothesService.findClothesbyClothesId(buyId, buyNum);
            if (buyclothes == null) {
                throw new BusinessException("products.notexist");
            }
            OrderItems orderItems = new OrderItems("item-" + (++count),
                    buyclothes, buyNum, buyclothes.getPrice() * buyNum);
            Order order = new Order();
            OrderIO orderIO = new OrderIO();
            orderIO.addOrders(order);
        }
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
