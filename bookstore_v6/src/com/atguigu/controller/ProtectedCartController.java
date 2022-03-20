package com.atguigu.controller;

import com.atguigu.base.BaseConstant;
import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.Book;
import com.atguigu.bojo.Cart;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/protected/cart")
public class ProtectedCartController extends ModelBaseServlet {

    public void addBookToCart(HttpServletRequest request, HttpServletResponse response) {
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

        try {
            response.sendRedirect(request.getContextPath() + "/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toCartPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            processTemplate("pages/cart/cart", request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(BaseConstant.SESSION_KEY_CART);
        try {
            response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCart(HttpServletRequest request, HttpServletResponse response) {
        String bookId = request.getParameter("bookId");
        Cart existCart = (Cart) request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);
        existCart.removeCartItem(Integer.parseInt(bookId));
        try {
            response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cartItemCountDecrease(HttpServletRequest request, HttpServletResponse response) {
        String bookId = request.getParameter("bookId");
        Cart existCart = (Cart) request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);
        Integer count = existCart.getCartItemMap().get(Integer.parseInt(bookId)).getCount();

        if (count == 1) {
            existCart.removeCartItem(Integer.parseInt(bookId));
        } else {
            existCart.cartItemCountDecrease(Integer.parseInt(bookId));
        }

        try {
            response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cartItemCountIncrease(HttpServletRequest request, HttpServletResponse response) {
        String bookId = request.getParameter("bookId");
        Cart existCart = (Cart) request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);
        existCart.cartItemCountIncrease(Integer.parseInt(bookId));
        try {
            response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCartItemCount(HttpServletRequest request, HttpServletResponse response) {
        String bookId = request.getParameter("bookId");
        String newCount = request.getParameter("newCount");
        Cart existCart = (Cart) request.getSession().getAttribute(BaseConstant.SESSION_KEY_CART);
        existCart.updateCartItemCount(Integer.parseInt(bookId), Integer.parseInt(newCount));
        try {
            response.sendRedirect(request.getContextPath() + "/protected/cart?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
