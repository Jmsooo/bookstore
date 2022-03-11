package com.atguigu.dao.test;

import com.atguigu.bojo.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.util.List;

public class TestUserDao {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void testAddUser() {
        User user = new User("jiaming", "jiaming", "jiaming@163.com");
        int row = userDao.addUser(user);
        System.out.println("row = " + row);
    }

    @Test
    public void testDeleteUserById() {
        int row = userDao.deleteUserById(4);
        System.out.println("row = " + row);
    }

    @Test
    public void testUpdateUserById() {
        int row = userDao.updateUserById(new User(5, "mjia", "mjia", "mjia@qq.com"));
        System.out.println("row = " + row);
    }

    @Test
    public void testSelectUserList() {
        List<User> users = userDao.selectUserList();
        System.out.println("users = " + users);
    }

    @Test
    public void testSelectUserById() {
        User user = userDao.selectUserById(5);
        System.out.println("user = " + user);
    }

}
