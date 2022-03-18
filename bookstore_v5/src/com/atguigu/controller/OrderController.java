package com.atguigu.controller;

import com.atguigu.base.BaseConstant;
import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.Cart;
import com.atguigu.bojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order")
public class OrderController extends ModelBaseServlet {

    public void checkout(HttpServletRequest request, HttpServletResponse response){
        //获取购物车信息
        Cart existCart = (Cart) request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);
        //获取登录用户信息
        User loginUser = (User) request.getSession().getAttribute(BaseConstant.SESSION_KEY_LOGIN_USER);
        OrderService orderService = new OrderServiceImpl();
        String orderSequence = orderService.checkout(existCart, loginUser);

    }


}
