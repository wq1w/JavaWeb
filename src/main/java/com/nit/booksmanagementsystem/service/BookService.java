package com.nit.booksmanagementsystem.service;

import com.nit.booksmanagementsystem.dao.BookDao;
import com.nit.booksmanagementsystem.entity.Book;

import java.util.List;

public class BookService {
    public BookDao bookDao = new BookDao();

    public List<Book> selectAll() {
        return bookDao.selectAll();
    }

    public List<Book> selectCondition(Book book) {
        return bookDao.selectCondition(book);
    }

    public void update(Book book) {
        bookDao.update(book);
    }

    public Book selectOne(int id) {
        return bookDao.selectById(id);
    }

    public void bookBorrow(int id, boolean isBorrowed) {
        bookDao.updateBorrow(id, isBorrowed);
    }

    public void insertOne(Book book) {
        bookDao.insertOne(book);
    }

    public void deleteOne(int id) {
        bookDao.deleteOne(id);
    }
}
