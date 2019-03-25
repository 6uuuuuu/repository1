package com.pmc.ui;

import com.pmc.bean.User;
import com.pmc.service.UserService;
import com.pmc.service.impl.UserServiceImpl;
import com.pmc.utills.BusinessException;

/**
 * 选择2后，用户进行注册
 */
public class RegisterClass extends BaseClass {
    /**
     * 进行注册的方法，因为使用了BaseClasss中的input，所以不能定义为static方法
     * 调用该方法时必须先新建RegisterClass的对象
     * @return
     * @throws BusinessException
     */
    public String Register() throws BusinessException {
        println(getString("input.username"));
        String userName = input.nextLine();//用户输入的用户名
        println(getString("input.password"));
        String passWord = input.nextLine();//录入用户输入的登录密码
        User user = new User(userName,passWord);//需要将这个新生成的对象给业务对象，进行业务操作
        UserService userService = new UserServiceImpl();
        return userService.register(user);
    }
}
