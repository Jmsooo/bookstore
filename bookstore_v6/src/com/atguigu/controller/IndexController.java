package com.atguigu.controller;

import com.atguigu.base.ViewBaseServlet;
import com.atguigu.bojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index.html")
public class IndexController extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所有图书列表
        BookService bookService = new BookServiceImpl();
        List<Book> bookList = null;
        try {
            bookList = bookService.selectBookList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("bookList", bookList);
        processTemplate("index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
