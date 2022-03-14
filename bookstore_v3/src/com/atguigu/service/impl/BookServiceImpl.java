package com.atguigu.service.impl;

import com.atguigu.bojo.Book;
import com.atguigu.bojo.PageInfo;
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

    @Override
    public PageInfo<Book> selectBookListByPage(Integer currentPage) {
        BookDao bookDao = new BookDaoImpl();

        PageInfo<Book> pageInfo = new PageInfo<>();
        pageInfo.setCurrentPage(currentPage);
        Integer pageSize = 5;
        pageInfo.setPageSize(pageSize);
        Long totalSize = bookDao.selectCount();
        pageInfo.setTotalSize(totalSize);
        Long totalPage = (totalSize % pageSize == 0) ? (totalSize / pageSize) : (totalSize / pageSize + 1);
        pageInfo.setTotalPage(totalPage);
        //⑤设置data:
        // select*fromt_booklimit?,?;
        // 第一个?:起始索引
        // 第二个?:每页记录数(偏移量)
        Integer begin = (currentPage - 1) * pageSize;
        List<Book> bookList = bookDao.selectBookListByPage(begin,pageSize);
        pageInfo.setData(bookList);
        return pageInfo;
    }

    @Override
    public int addBook(Book book) {
        BookDao bookDao = new BookDaoImpl();
        return bookDao.addBook(book);
    }
}
