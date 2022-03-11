package com.atguigu.dao.impl;

import com.atguigu.bojo.User;
import com.atguigu.dao.UserDao;
import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
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
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().update(
                    connection,
                    "insert into t_user values(null,?,?,?)",
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }

    @Override
    public int deleteUserById(Integer id) {
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().update(
                    connection,
                    "delete from t_user where user_id = ?",
                    id
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }

    @Override
    public int UpdateUserById(User user) {
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().update(
                    connection,
                    "update t_user set user_name = ? , user_pwd = ? , email = ? where user_id = ?",
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getId()

            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }

    @Override
    public List<User> selectUserList() {
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().query(
                    connection,
                    "select user_id id,user_name username,user_pwd password,email from t_user",
                    new BeanListHandler<User>(User.class)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }

    @Override
    public User selectUserById(Integer id) {
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().query(
                    connection,
                    "select user_id id,user_name username,user_pwd password,email from t_user where id = ?",
                    new BeanHandler<User>(User.class),
                    id
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }
}
