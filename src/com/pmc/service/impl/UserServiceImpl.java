package com.pmc.service.impl;

import com.pmc.bean.User;
import com.pmc.service.UserService;
import com.pmc.utills.BusinessException;
import com.pmc.utills.EmptyUtils;
import com.pmc.utills.UserIO;

public class UserServiceImpl implements UserService {
    boolean flag = false;

    /**
     * 对注册的用户信息进行操作，比如存储
     * 使用数据对象流来对数据进行操作
     *
     * @param user
     * @return
     * @throws BusinessException
     */
    @Override
    public boolean register(User user) throws BusinessException {
        UserIO userIO = new UserIO();
        if(userIO.findUsers(user)){
            throw new BusinessException("user.exist");//用户已存在时不能注册
        }
        userIO.addUsers(user);
        userIO.writeUsers(user);//注册成功or失败
        return true;
    }

    @Override
    public boolean login(User user) throws BusinessException {
        if (EmptyUtils.isEmpty(user.getUsername())) {
            throw new BusinessException("username.notnull");
        }
        if (EmptyUtils.isEmpty(user.getPassword())) {
            throw new BusinessException("password.notnull");
        }
        UserIO userIO = new UserIO();
        flag = userIO.findUsers(user);//登录成功or失败
        if (flag) {
            return true;
        } else {
            throw new BusinessException("login.error");
        }

    }
}
