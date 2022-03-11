package com.atguigu.service;

import com.atguigu.bojo.User;

public interface UserService {

    public User login(String username,String password);

    public int regist(User user);

    public User selectUserByName(String username);

}
