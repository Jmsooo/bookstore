package com.atguigu.controller;

import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.Book;
import com.atguigu.bojo.PageInfo;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookManager")
public class BookManagerController extends ModelBaseServlet {

    public void toBookManagerPage(HttpServletRequest request, HttpServletResponse response){
        try {
            String currentPageStr = request.getParameter("currentPage");
            Integer currentPage = 1;
            System.out.println("currentPageStr.isEmpty() = " + currentPageStr.isEmpty());
            if (!currentPageStr.isEmpty()){
                currentPage = Integer.parseInt(currentPageStr);
            }
            //分页查询
            BookService bookService = new BookServiceImpl();
            PageInfo<Book> pageInfo = bookService.selectBookListByPage(currentPage);
            request.setAttribute("pageInfo",pageInfo);
            processTemplate("pages/manager/book_manager",request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
