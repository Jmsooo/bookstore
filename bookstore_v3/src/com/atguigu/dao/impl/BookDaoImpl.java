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

    @Override
    public Long selectCount() {
        return selectCount(
                "select count(*) from t_book"
        );
    }

    @Override
    public List<Book> selectBookListByPage(Integer begin, Integer pageSize) {
        return selectAll(
                Book.class,
                "select book_id bookId,book_name bookName,author,price,sales,stock,img_path imgPath from t_book limit ?,?",
                begin,
                pageSize
        );
    }

    @Override
    public int addBook(Book book) {
        return update(
                "insert into t_book values(null,?,?,?,?,?,?)",
                book.getBookName(),
                book.getAuthor(),
                book.getPrice(),
                book.getSales(),
                book.getStock(),
                "static/uploads/santi.jpg"
        );
    }
}
