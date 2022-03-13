package com.atguigu.dao;

import com.atguigu.bojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> selectBookList();
}
