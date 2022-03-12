package com.atguigu.controller;

import com.atguigu.bojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/regist")
public class RegistController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();

        try {
            BeanUtils.populate(user, req.getParameterMap());

            //校验注册用户是否存在
            UserService userService = new UserServiceImpl();
            User existUser = userService.selectUserByName(user.getUsername());

            if (existUser != null) {
                //用户存在
                resp.setContentType("text/html;charset=utf-8");
                String errorMsg = "用户已经存在!";
                resp.getWriter().write(errorMsg);
                return;
            }

            //用户不存在 执行注册
            int row = userService.regist(user);

            if (row >= 1) {
                //注册成功 重定向到 regist_success.html
                resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.html");
            } else {
                //注册失败 转发到 regist.html
                req.getRequestDispatcher("/pages/user/regist.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("/pages/user/regist.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
