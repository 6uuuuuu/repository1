package com.pmc.ui;

import com.pmc.bean.Clothes;
import com.pmc.service.ClothesService;
import com.pmc.service.impl.ClothesServiceImpl;
import com.pmc.utills.ConsoleTable;

import java.util.List;

/**
 * 登陆后进入的主界面，展示商品
 */
public class HomeClass extends BaseClass {
    public void show() {
        showClothesList();//展示商品
        boolean flag = true;
        while(flag) {
            println(getString("home.function"));//1、查询全部订单 2、查找订单 3、购买 0、退出
            println(getString("info.select"));//请选择
            String s = input.nextLine();//读取用户输入的内容
            switch (s) {
                case "1":
                    flag = false;
                    findAllOrders();
                    break;
                case "2":
                    flag = false;
                    findOrder();
                    break;
                case "3":
                    flag = false;
                    buyClothes();
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

    private void buyClothes() {
    }

    private void findOrder() {
    }

    private void findAllOrders() {
    }

    private void showClothesList() {
        ClothesService clothesService = new ClothesServiceImpl();
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
