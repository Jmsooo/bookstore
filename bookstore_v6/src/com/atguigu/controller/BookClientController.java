package com.atguigu.controller;

import com.atguigu.base.ModelBaseServlet;
import com.atguigu.bojo.Book;
import com.atguigu.bojo.ResultVO;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/book")
public class BookClientController extends ModelBaseServlet {

    public void selectBookList(HttpServletRequest request, HttpServletResponse response){
        BookService bookService = new BookServiceImpl();
        ResultVO resultVO = null;
        try {
            List<Book> bookList = bookService.selectBookList();
            resultVO = new ResultVO(true,"图书列表查询成功！",bookList);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(false,"查询图书列表失败！",null);
        }
        JsonUtils.javaBean2ResponseText(response,resultVO);
    }

}
