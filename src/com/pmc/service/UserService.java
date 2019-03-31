package com.pmc.service;

import com.pmc.bean.User;
import com.pmc.utills.BusinessException;

/**
 * 对用户进行业务操作
 * 接口，便于未来替换实现类
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     * @throws BusinessException
     */
    public void register(User user) throws BusinessException;

    /**
     * 用户登录
     * @param user
     * @return
     * @throws BusinessException
     */
    public void login(User user) throws BusinessException;
}
