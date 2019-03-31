package com.pmc.ui;

import com.pmc.bean.User;
import com.pmc.service.UserService;
import com.pmc.service.impl.UserServiceImpl;

/**
 * 选择1后，用户进行登录
 */
public class LoginClass extends BaseClass {
    //进行登录的方法
    public User login(){
        println(getString("input.username"));
        String userName = input.nextLine();
        println(getString("input.password"));
        String passWord = input.nextLine();
        User user = new User(userName,passWord);
        UserService userService = new UserServiceImpl();
        userService.login(user);
        return user;
    }
}
