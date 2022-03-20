package com.atguigu.controller;

import com.atguigu.base.BaseConstant;
import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.Book;
import com.atguigu.bojo.Cart;
import com.atguigu.bojo.ResultVO;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class CartController extends ModelBaseServlet {

    public void selectCart(HttpServletRequest request,HttpServletResponse response){
        Object existCart = request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);
        ResultVO resultVO = new ResultVO(true,"查询购物车成功！",existCart);
        JsonUtils.javaBean2ResponseText(response,resultVO);
    }

    public void addBookToCart(HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = null;
        String bookId = request.getParameter("bookId");
        BookService bookService = new BookServiceImpl();
        Book book = bookService.selectBookById(Integer.parseInt(bookId));

        Cart existCart = (Cart) request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);

        if (existCart == null) {
            //第一次加入购物车
            existCart = new Cart();
        } else {
            //不是第一次
        }

        existCart.addBookToCart(book);
        request.getSession().setAttribute(BaseConstant.SESSION_KEY_CART, existCart);

        resultVO = new ResultVO(true,"添加购物车成功！",null);

        JsonUtils.javaBean2ResponseText(response,resultVO);
    }


}
