package com.nit.booksmanagementsystem.dao;

import com.nit.booksmanagementsystem.entity.BookBookType;
import com.nit.booksmanagementsystem.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookBookTypeDao {

    public List<BookBookType> selectType() {
        String sql = "select * from book_book_type";
        return resultSetToBookBookTypeList(JdbcUtil.query(sql));
    }

    public List<BookBookType> resultSetToBookBookTypeList(ResultSet resultSet) {
        try {
            List<BookBookType> bookBookTypes = new ArrayList<>();
            while (resultSet.next()) {
                BookBookType bookBookType = new BookBookType();
                bookBookType.setId(resultSet.getInt(1));
                bookBookType.setBookId(resultSet.getInt(2));
                bookBookType.setBookTypeId(resultSet.getInt(3));
                bookBookTypes.add(bookBookType);
            }
            return bookBookTypes;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BookBookType selectOne(int id) {
        String sql = "select * from book_book_type where id = ?";
        List<BookBookType> bookBookTypes = resultSetToBookBookTypeList(JdbcUtil.query(sql, id));
        return bookBookTypes.size() > 0 ? bookBookTypes.get(0) : null;
    }

    public void insertOne(BookBookType bookBookType) {
        String sql = "INSERT INTO `book_book_type` (book_id, book_type_id) VALUES (?, ?)";
        JdbcUtil.insert(sql, true, bookBookType.getBookId(), bookBookType.getBookTypeId());
    }

    public void deleteOne(int id) {
        String sql = "delete from `book_book_type` where id = ?";
        JdbcUtil.execute(sql, id);
    }

    public void updateOne(BookBookType bookBookType) {
        String sql = "update `book_book_type` set book_id= ?, book_type_id = ? WHERE id = ?";
        JdbcUtil.execute(sql, bookBookType.getBookId(), bookBookType.getBookTypeId(), bookBookType.getId());
    }
}