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

@WebServlet("/bookManager")
public class BookManagerController extends ModelBaseServlet {

    public void toBookManagerPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String currentPageStr = request.getParameter("currentPage");
            Integer currentPage = 1;

            if (!currentPageStr.isEmpty()) {
                currentPage = Integer.parseInt(currentPageStr);
            }

            //分页查询
            BookService bookService = new BookServiceImpl();
            PageInfo<Book> pageInfo = bookService.selectBookListByPage(currentPage);
            request.setAttribute("pageInfo", pageInfo);
            processTemplate("pages/manager/book_manager", request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBook(HttpServletRequest request, HttpServletResponse response) {
        Book book = new Book();
        try {
            BeanUtils.populate(book, request.getParameterMap());

            BookService bookService = new BookServiceImpl();
            int row = bookService.addBook(book);

            if (row >= 1) {
                //添加成功
                response.sendRedirect(request.getContextPath() + "/bookManager?method=toBookManagerPage&currentPage=1");
            } else {
                //添加失败 - 数据回写
                request.setAttribute("book", book);
                toAddBookPage(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //异常 - 数据回写
            request.setAttribute("book", book);
            toAddBookPage(request, response);
        }
    }

    public void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        String bookIdStr = request.getParameter("bookId");
        Integer bookId = Integer.parseInt(bookIdStr);

        try {
            BookService bookService = new BookServiceImpl();
            bookService.deleteBook(bookId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.sendRedirect(request.getContextPath() + "/bookManager?method=toBookManagerPage&currentPage=1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBook(HttpServletRequest request, HttpServletResponse response){
        Book book = new Book();
        try {
            BeanUtils.populate(book,request.getParameterMap());

            BookService bookService = new BookServiceImpl();
            int row = bookService.updateBook(book);

            if (row >= 1){
                //修改成功
                response.sendRedirect(request.getContextPath() + "/bookManager?method=toBookManagerPage&currentPage=1");
            }else {
                //修改失败 - 数据回写
                request.setAttribute("book",book);
                processTemplate("pages/manager/book_edit",request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //异常 - 数据回写
                request.setAttribute("book",book);
                processTemplate("pages/manager/book_edit",request,response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void toAddBookPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            processTemplate("pages/manager/book_add", request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toUpdateBookPage(HttpServletRequest request, HttpServletResponse response){
        try {
            String bookIdStr = request.getParameter("bookId");
            Integer bookId = Integer.parseInt(bookIdStr);

            BookService bookService = new BookServiceImpl();
            Book book = bookService.selectBookById(bookId);

            request.setAttribute("book",book);
            processTemplate("pages/manager/book_edit",request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
