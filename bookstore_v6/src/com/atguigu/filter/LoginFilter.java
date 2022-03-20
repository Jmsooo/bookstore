package com.atguigu.filter;

import com.atguigu.base.BaseConstant;
import com.atguigu.bojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User loginUser = (User) request.getSession().getAttribute(BaseConstant.SESSION_KEY_LOGIN_USER);
        if (loginUser == null){
            //没有登录
            response.sendRedirect(request.getContextPath()+"/user?method=toLoginPage");
        }else {
            filterChain.doFilter(request,response);
        }

    }

}
