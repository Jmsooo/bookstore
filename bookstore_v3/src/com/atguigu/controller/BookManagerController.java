package com.atguigu.controller;

import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.Book;
import com.atguigu.bojo.PageInfo;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

    public void addBook(HttpServletRequest request, HttpServletResponse response){
        Book book = new Book();
        try {
            BeanUtils.populate(book,request.getParameterMap());

            BookService bookService = new BookServiceImpl();
            int row = bookService.addBook(book);

            if (row >= 1){
                //添加成功
                response.sendRedirect(request.getContextPath() + "/bookManager?method=toBookManagerPage&currentPage=1");
            }else {
                //添加失败
                request.setAttribute("book",book);
                toAddBookPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toAddBookPage(HttpServletRequest request, HttpServletResponse response){
        try {
            processTemplate("pages/manager/book_add",request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
