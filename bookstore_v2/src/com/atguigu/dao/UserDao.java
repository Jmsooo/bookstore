package com.atguigu.dao;

import com.atguigu.bojo.User;

import java.util.List;

public interface UserDao {
    public User login(String username,String password);

    public int addUser(User user);

    public int deleteUserById(Integer id);

    public int UpdateUserById(User user);

    public List<User> selectUserList();

    public User selectUserById(Integer id);
}
