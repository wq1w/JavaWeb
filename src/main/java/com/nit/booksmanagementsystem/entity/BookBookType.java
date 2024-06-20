package com.nit.booksmanagementsystem.entity;

/**
 * 图书分类实体类
 * 用于表示一本书可能属于多种分类的关系
 * 包含三个属性:
 * 1. id: id
 * 2. bookId: 图书ID
 * 3. bookTypeId: 图书分类ID
 */
public class BookBookType {
    // id
    int id;
    // 图书ID
    int bookId;
    // 图书分类ID
    int bookTypeId;

    // id的getter和setter方法
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // bookId的getter和setter方法
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    // bookTypeId的getter和setter方法
    public int getBookTypeId() {
        return bookTypeId;
    }
    public void setBookTypeId(int bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    // 重写toString()方法,用于打印对象信息
    @Override
    public String toString() {
        return "BookBookType{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", bookTypeId=" + bookTypeId +
                '}';
    }
}