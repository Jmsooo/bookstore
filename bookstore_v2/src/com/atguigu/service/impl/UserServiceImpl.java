package com.atguigu.service.impl;

import com.atguigu.bojo.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User login(User user) {
        UserDao userDao = new UserDaoImpl();
        User loginUser = userDao.login(user);
        return loginUser;
    }
}
