package com.atguigu.service.impl;

import com.atguigu.bojo.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public int regist(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User selectUserByName(String username) {
        return userDao.selectUserByName(username);
    }
}
