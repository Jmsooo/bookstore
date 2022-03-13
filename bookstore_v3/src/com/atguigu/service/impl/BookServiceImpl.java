package com.atguigu.service.impl;

import com.atguigu.bojo.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> selectBookList() {
        BookDao bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.selectBookList();
        return bookList;
    }
}
