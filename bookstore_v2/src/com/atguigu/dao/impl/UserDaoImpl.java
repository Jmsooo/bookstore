package com.atguigu.dao.impl;

import com.atguigu.base.BaseDao;
import com.atguigu.bojo.User;
import com.atguigu.dao.UserDao;

import java.sql.Connection;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    Connection connection = null;

    @Override
    public User login(String username, String password) {
        User user = null;
        if ("admin".equals(username) && "123456".equals(password)) {
            user = new User(1, username, password, "123456@qq.com");
            return user;
        } else {
            return null;
        }
    }

    @Override
    public int addUser(User user) {
        return update(
                "insert into t_user values(null,?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

    @Override
    public int deleteUserById(Integer id) {
        return update(
                "delete from t_user where user_id = ?",
                id
        );
    }

    @Override
    public int updateUserById(User user) {
        return update(
                "update t_user set user_name = ? , user_pwd = ? , email = ? where user_id = ?",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getId()
        );
    }

    @Override
    public List<User> selectUserList() {
        return selectAll(
                User.class,
                "select user_id id,user_name username,user_pwd password,email from t_user"
        );
    }

    @Override
    public User selectUserById(Integer id) {
        return selectOne(
                User.class,
                "select user_id id,user_name username,user_pwd password,email from t_user where user_id = ?",
                id
        );
    }

    @Override
    public User selectUserByName(String username) {
        return selectOne(
                User.class,
                "select user_id id,user_name username,user_pwd password,email from t_user where user_name = ?",
                username
        );
    }
}
