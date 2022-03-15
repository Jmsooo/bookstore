package com.atguigu.controller;

import com.atguigu.base.BaseConstant;
import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends ModelBaseServlet {

    public void login(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("password = " + password);

        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(username, password);

        try {
            if (loginUser != null) {
                req.getSession().setAttribute("loginUser",loginUser);
                resp.sendRedirect(req.getContextPath() + "/user?method=toLoginSuccessPage");
            } else {
                String errorMsg = "账户或密码错误";
                req.setAttribute("errorMsg",errorMsg);
                toLoginPage(req, resp);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = "账户或密码错误";
            req.setAttribute("errorMsg",errorMsg);
        }
    }

    public void regist(HttpServletRequest req, HttpServletResponse resp){
        User user = new User();

        try {
            BeanUtils.populate(user, req.getParameterMap());

            //校验注册用户是否存在
            UserService userService = new UserServiceImpl();
            User existUser = userService.selectUserByName(user.getUsername());

            if (existUser != null) {
                //用户存在
                String errorMsg = "用户已经存在!";
                req.setAttribute("errorMsg",errorMsg);
                toRegistPage(req, resp);
                return;
            }

            //用户不存在 执行注册
            int row = userService.regist(user);

            if (row >= 1) {
                //注册成功 重定向到 regist_success.html
                resp.sendRedirect(req.getContextPath() + "/user?method=toRegistSuccessPage");
            } else {
                //注册失败 转发到 regist.html
                toRegistPage(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("/pages/user/regist.html");
        }
    }

    public void toRegistPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            processTemplate("pages/user/regist",req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toRegistSuccessPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            processTemplate("pages/user/regist_success",req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toLoginPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            processTemplate("pages/user/login",req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toLoginSuccessPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            processTemplate("pages/user/login_success",req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
