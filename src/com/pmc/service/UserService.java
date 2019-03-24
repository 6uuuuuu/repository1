package com.pmc.service;

import com.pmc.bean.User;
import com.pmc.utills.BusinessException;

/**
 * 对用户进行业务操作
 * 接口，便于未来替换实现类
 */
public interface UserService {
    /**
     *
     * @param user
     * @return User
     * 用户注册
     * @throws BusinessException
     */
    public User register(User user) throws BusinessException;
}
