package com.atguigu.controller;

import com.atguigu.base.BaseConstant;
import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.Book;
import com.atguigu.bojo.Cart;
import com.atguigu.bojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cart")
public class CartController extends ModelBaseServlet {

    public void addBookToCart(HttpServletRequest request, HttpServletResponse response){
        String bookId = request.getParameter("bookId");
        BookService bookService = new BookServiceImpl();
        Book book = bookService.selectBookById(Integer.parseInt(bookId));

        Cart existCart = (Cart) request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);

        if (existCart == null){
            //第一次加入购物车
            existCart = new Cart();
        }else {
            //不是第一次
        }

        existCart.addBookToCart(book);
        request.getSession().setAttribute(BaseConstant.SESSION_KEY_CART,existCart);

        try {
            response.sendRedirect(request.getContextPath() + "/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toCartPage(HttpServletRequest request, HttpServletResponse response){
        try {
            processTemplate("pages/cart/cart",request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute(BaseConstant.SESSION_KEY_CART);
        toCartPage(request, response);
    }

    public void deleteCart(HttpServletRequest request,HttpServletResponse response){
        String bookId = request.getParameter("bookId");
        Cart existCart = (Cart) request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);
        existCart.removeCartItem(Integer.parseInt(bookId));
        toCartPage(request, response);
    }


}
