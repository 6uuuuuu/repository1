package com.pmc.service.impl;

import com.pmc.bean.User;
import com.pmc.service.UserService;
import com.pmc.utills.BusinessException;
import com.pmc.utills.EmptyUtils;
import com.pmc.utills.UserIO;

public class UserServiceImpl implements UserService {
    boolean flag =false;
    /**
     * 对注册的用户信息进行操作，比如存储
     * 使用数据对象流来对数据进行操作
     * @param user
     * @return
     * @throws BusinessException
     */
    @Override
    public String register(User user) {
        UserIO userIO = new UserIO();
        userIO.addUsers(user);
        try {
            flag = userIO.writeUsers(user);//注册成功or失败
        } catch (BusinessException e) {
            return "reg.error";
        }
            return "reg.success";
        }

    @Override
    public String login(User user) {
        if(EmptyUtils.isEmpty(user.getUsername())){
            return "username.cannotbenull";
        }
        if(EmptyUtils.isEmpty(user.getPassword())){
            return "password.cannotbenull";
        }
        UserIO userIO = new UserIO();
        flag = userIO.findUsers(user);//登录成功or失败
        if (flag) {
            return "login.success";
        } else {
            return "login.error";
        }
    }
}
