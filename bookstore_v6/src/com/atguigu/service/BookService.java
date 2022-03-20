package com.atguigu.service;

import com.atguigu.bojo.Book;
import com.atguigu.bojo.PageInfo;

import java.util.List;

public interface BookService {
    public List<Book> selectBookList();

    PageInfo<Book> selectBookListByPage(Integer currentPage);

    int addBook(Book book);

    void deleteBook(Integer bookId);

    Book selectBookById(Integer bookId);

    int updateBook(Book book);
}
