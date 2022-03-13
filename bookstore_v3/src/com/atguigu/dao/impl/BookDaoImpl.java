package com.atguigu.dao.impl;

import com.atguigu.base.BaseDao;
import com.atguigu.bojo.Book;
import com.atguigu.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> selectBookList() {
        return selectAll(
                Book.class,
                "select book_id bookId,book_name bookName,author,price,sales,stock,img_path imgPath from t_book"
        );
    }
}
