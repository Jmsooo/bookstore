package com.atguigu.controller;

import com.atguigu.base.BaseConstant;
import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends ModelBaseServlet {

    public void login(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(username, password);

        try {
            if (loginUser != null) {
                //将用户信息存入session 实现保持登录功能
                req.getSession().setAttribute(BaseConstant.SESSION_KEY_LOGIN_USER,loginUser);
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

            //页面输入的验证码
            String code = req.getParameter("code");
            //获取kaptcha中的验证码
            String kaptcha = (String) req.getSession().getAttribute(BaseConstant.SESSION_kEY_REGIST_CODE);

            if (existUser != null) {
                //用户存在
                String errorMsg = "用户已经存在!";
                req.setAttribute("errorMsg",errorMsg);
                toRegistPage(req, resp);
                return;
            }

            if (!code.equals(kaptcha)){
                //验证码匹配不对
                String errorCode = "验证码错误";
                req.setAttribute("errorCode",errorCode);
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

    public void logout(HttpServletRequest req, HttpServletResponse resp){
        req.getSession().removeAttribute(BaseConstant.SESSION_KEY_LOGIN_USER);
        try {
            resp.sendRedirect(req.getContextPath()+"/index.html");
        } catch (IOException e) {
            e.printStackTrace();
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
