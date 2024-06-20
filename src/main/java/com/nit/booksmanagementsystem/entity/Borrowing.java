package com.nit.booksmanagementsystem.entity;

import java.sql.Timestamp;

/**
 * 借阅记录实体类
 * 表示一条图书借阅记录的相关信息
 */
public class Borrowing {
    /**
     * 借阅记录ID
     */
    int id;

    /**
     * 借阅人用户ID
     */
    int userId;

    /**
     * 借阅的图书ID
     */
    int bookId;

    /**
     * 借/还 类型, "borrowing" 表示借阅, "returning" 表示归还
     */
    String type;

    /**
     * 借阅/归还时间
     */
    Timestamp datetime;

    /**
     * 重写 toString 方法,用于输出 Borrowing 对象的字符串表示
     * @return Borrowing 对象的字符串表示
     */
    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", type='" + type + '\'' +
                ", datetime=" + datetime +
                '}';
    }

    /**
     * 获取借阅记录ID
     * @return 借阅记录ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置借阅记录ID
     * @param id 借阅记录ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取借阅人用户ID
     * @return 借阅人用户ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置借阅人用户ID
     * @param userId 借阅人用户ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取借阅的图书ID
     * @return 借阅的图书ID
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * 设置借阅的图书ID
     * @param bookId 借阅的图书ID
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取借/还类型
     * @return 借/还类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置借/还类型
     * @param type 借/还类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取借阅/归还时间
     * @return 借阅/归还时间
     */
    public Timestamp getDatetime() {
        return datetime;
    }

    /**
     * 设置借阅/归还时间
     * @param datetime 借阅/归还时间
     */
    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}