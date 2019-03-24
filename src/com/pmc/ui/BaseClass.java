package com.pmc.ui;


import com.pmc.bean.User;

import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 父类，所有界面需要用到的公共的东西
 */
public abstract class BaseClass {
    protected Scanner input = new Scanner(System.in);//读取用户输入内容
    protected static User currentUser;//当前用户对象
    private static ResourceBundle resource = ResourceBundle.getBundle("com.pmc.resource.r");

    /*
    输出资源文件的内容
     */
    public static String getString(String key){
       return resource.getString(key);
    }

    public static void print(String s){
        System.out.print(s);
    }
    public static void println(String s){
        System.out.println(s);
    }
}
