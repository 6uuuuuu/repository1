package com.pmc.ui;

/**
 * 欢迎登陆界面
 */
public class WelcomClass extends BaseClass{
    public void start(){
        println(getString("info.welcome"));
        String result = "";
        boolean flag = true;
        while(flag){
            println(getString("info.login.reg"));//1.登陆 2.注册
            println(getString("info.select"));//请选择
            String s = input.nextLine();//读取用户输入的内容
            switch (s){
                case "1":
                    flag = false;
                    //System.out.println("登陆");

                    break;
                case "2":
                    flag = false;
                    //System.out.println("注册");
                    result = new RegisterClass().Register();
                    println(getString(result));
                    break;
                default:
                    println(getString("input.error"));//输入错误则继续读取直到用户输入正确的信息
                    break;
            }

        }
    }
}
