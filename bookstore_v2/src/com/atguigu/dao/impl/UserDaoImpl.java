package com.atguigu.dao.impl;

import com.atguigu.bojo.User;
import com.atguigu.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(User user) {
        if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())){
            return user;
        }else {
            return null;
        }
    }
}
