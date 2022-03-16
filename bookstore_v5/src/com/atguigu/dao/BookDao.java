package com.atguigu.dao;

import com.atguigu.bojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> selectBookList();

    Long selectCount();

    List<Book> selectBookListByPage(Integer begin, Integer pageSize);

    int addBook(Book book);

    void deleteBook(Integer bookId);

    Book selectBookById(Integer bookId);

    int updateBook(Book book);
}
