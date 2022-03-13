package com.atguigu.dao;

import com.atguigu.bojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> selectBookList();

    Long selectCount();

    List<Book> selectBookListByPage(Integer begin, Integer pageSize);
}
