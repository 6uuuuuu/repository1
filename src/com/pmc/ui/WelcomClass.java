package com.pmc.ui;

import com.pmc.utills.BusinessException;
import com.pmc.utills.UserIO;

/**
 * 欢迎登陆界面
 */
public class WelcomClass extends BaseClass {
    public void start() {
        println(getString("info.welcome"));
        UserIO userIO = new UserIO();
        userIO.readUsers();//初始化users，读取全部用户信息
        String result = "";
        boolean flag = true;

        while (flag) {
            println(getString("info.login.reg"));//1.登陆 2.注册
            println(getString("info.select"));//请选择
            String s = input.nextLine();//读取用户输入的内容
            switch (s) {
                case "1":
                    try {
                        result = new LoginClass().login();
                        println(getString(result));
                        flag = false;
                    } catch (BusinessException e) {
                        println(getString(e.getMessage()));
                    }
                    break;
                case "2":
                    try {
                    result = new RegisterClass().Register();
                    println(getString(result));
                    flag = false;
                    } catch (BusinessException e) {
                        println(getString(e.getMessage()));
                    }
                    break;
                default:
                    println(getString("input.error"));//输入错误则继续读取直到用户输入正确的信息
                    break;
            }
        }

        //循环结束后进入展示商品的主界面
        HomeClass home = new HomeClass();
        home.show();
    }
}
