package com.nit.booksmanagementsystem.dao;

import com.nit.booksmanagementsystem.entity.Borrowing;
import com.nit.booksmanagementsystem.utils.JdbcUtil;
import com.nit.booksmanagementsystem.vo.BorrowingVo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDao {
    public void insertOne(Borrowing borrowing) {
        String sql = "INSERT INTO `borrowing` (user_id, book_id, type, datetime) VALUES (?, ?, ?, ?)";
        JdbcUtil.insert(sql, true, borrowing.getUserId(), borrowing.getBookId(), borrowing.getType(), borrowing.getDatetime());
    }

    public List<BorrowingVo> selectVoAll() {
        String sql = "select borrowing.id, username, `name` book_name, type, `datetime` from borrowing, `user`, book where borrowing.book_id = book.id and borrowing.user_id = `user`.id;";
        ResultSet resultSet = JdbcUtil.query(sql);
        return getBorrowingVo(resultSet);
    }

    public List<BorrowingVo> selectVoByUserId(int id) {
        String sql = "select borrowing.id, username, `name` book_name, type, `datetime` from borrowing, `user`, book where borrowing.book_id = book.id and borrowing.user_id = `user`.id and user_id = ?";
        ResultSet resultSet = JdbcUtil.query(sql, id);
        return getBorrowingVo(resultSet);
    }

    private List<BorrowingVo> getBorrowingVo(ResultSet resultSet) {
        try {
            List<BorrowingVo> borrowings = new ArrayList<>();
            while (resultSet.next()) {
                BorrowingVo borrowing = new BorrowingVo();
                borrowing.setId(resultSet.getInt(1));
                borrowing.setUsername(resultSet.getString(2));
                borrowing.setBookName(resultSet.getString(3));
                borrowing.setTypeCn(resultSet.getString(4).equals("borrowing") ? "借" : "还");
                borrowing.setDatetime(resultSet.getTimestamp(5));
                borrowings.add(borrowing);
            }
            return borrowings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
