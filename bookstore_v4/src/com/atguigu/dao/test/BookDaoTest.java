package com.atguigu.dao.test;

import com.atguigu.bojo.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    @Test
    public void selectBookList() {

        BookDao bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.selectBookList();
        System.out.println(bookList);

    }
}