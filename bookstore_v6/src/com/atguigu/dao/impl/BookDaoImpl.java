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

    @Override
    public void deleteBook(Integer bookId) {
        update(
                "delete from t_book where book_id = ?",
                bookId
        );
    }

    @Override
    public Book selectBookById(Integer bookId) {
        return selectOne(
                Book.class,
                "select book_id bookId,book_name bookName,author,price,sales,stock,img_path imgPath from t_book where book_id = ?",
                bookId
        );
    }

    @Override
    public int updateBook(Book book) {
        return update(
                "update t_book set book_name = ? , author = ? , price = ?, sales = ? , stock = ? where book_id = ?",
                book.getBookName(),
                book.getAuthor(),
                book.getPrice(),
                book.getSales(),
                book.getStock(),
                book.getBookId()
        );
    }

    @Override
    public void updateBooks(Object[][] bookParams) {
        batch(
                "update t_book set sales = sales + ?, stock = stock - ? where book_id = ?",
                bookParams
        );
    }
}
