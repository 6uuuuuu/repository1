package com.pmc.service.impl;

import com.pmc.bean.User;
import com.pmc.service.UserService;
import com.pmc.utils.BusinessException;
import com.pmc.utils.EmptyUtils;
import com.pmc.utils.UserIO;

public class UserServiceImpl implements UserService {

    /**
     * 对注册的用户信息进行操作，比如存储
     * 使用数据对象流来对数据进行操作
     *
     * @param user
     * @return
     * @throws BusinessException
     */
    @Override
    public void register(User user) throws BusinessException {
        UserIO userIO = new UserIO();
        if(userIO.findUserNames(user)){
            throw new BusinessException("user.exist");//用户已存在时不能注册
        }
        userIO.addUsers(user);
        userIO.writeUsers(user);//注册成功or失败
    }

    @Override
    public void login(User user) throws BusinessException {
        if (EmptyUtils.isEmpty(user.getUsername())) {
            throw new BusinessException("username.notnull");
        }
        if (EmptyUtils.isEmpty(user.getPassword())) {
            throw new BusinessException("password.notnull");
        }
        UserIO userIO = new UserIO();
        boolean flag = userIO.findUsers(user);//登录成功or失败
        if (!flag) {
            //未查找到用户则登录失败
            throw new BusinessException("login.error");
        }

    }
}
