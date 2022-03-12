package com.atguigu.service.impl;

import com.atguigu.bojo.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;
import com.atguigu.utils.MD5Utils;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        //先查询该用户是否存在
        User user = userDao.selectUserByName(username);
        if (user == null){
            return null;
        }

        boolean verify = MD5Utils.verify(password, user.getPassword());
        return verify ? user : null;
    }

    @Override
    public int regist(User user) {
        //对密码进行MD5加密
        String password = user.getPassword();
        String salt = MD5Utils.generateSalt();
        String md5Password = MD5Utils.generateMD5AndSalt(password, salt);
        user.setPassword(md5Password);
        return userDao.addUser(user);
    }

    @Override
    public User selectUserByName(String username) {
        return userDao.selectUserByName(username);
    }
}
