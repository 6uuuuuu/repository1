package com.pmc.service.impl;

import com.pmc.bean.User;
import com.pmc.service.UserService;
import com.pmc.utills.BusinessException;
import com.pmc.utills.UserIO;

public class UserServiceImpl implements UserService {
    /**
     * 对注册的用户信息进行操作，比如存储
     * 使用数据对象流来对数据进行操作
     * @param user
     * @return
     * @throws BusinessException
     */
    @Override
    public String register(User user) throws BusinessException {
        UserIO userIO = new UserIO();
        userIO.addUsers(user);
        boolean flag = userIO.writeUsers(user);//写入成功or失败
        if (flag) {
            return "reg.write.success";
        } else {
            return "reg.write.error";
        }
    }
}
