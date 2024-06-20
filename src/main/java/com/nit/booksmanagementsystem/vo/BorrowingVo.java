package com.nit.booksmanagementsystem.vo;

import com.nit.booksmanagementsystem.entity.Borrowing;

/**
 * 借阅记录视图对象
 * 继承自 Borrowing 实体类,并添加了用户名、图书名称和借/还类型中文表示的属性
 */
public class BorrowingVo extends Borrowing {

    /**
     * 借阅人用户名
     */
    public String username;

    /**
     * 借阅书籍名称
     */
    public String bookName;

    /**
     * 借/还类型的中文表示
     */
    public String typeCn;

    /**
     * 获取借阅人用户名
     * @return 借阅人用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置借阅人用户名
     * @param username 借阅人用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取借阅书籍名称
     * @return 借阅书籍名称
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置借阅书籍名称
     * @param bookName 借阅书籍名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取借/还类型的中文表示
     * @return 借/还类型的中文表示
     */
    public String getTypeCn() {
        return typeCn;
    }

    /**
     * 设置借/还类型的中文表示
     * @param typeCn 借/还类型的中文表示
     */
    public void setTypeCn(String typeCn) {
        this.typeCn = typeCn;
    }

    /**
     * 重写 toString 方法,用于输出 BorrowingVo 对象的字符串表示
     * @return BorrowingVo 对象的字符串表示
     */
    @Override
    public String toString() {
        return "BorrowingVo{" +
                "username='" + username + '\'' +
                ", bookName='" + bookName + '\'' +
                ", typeCn='" + typeCn + '\'' +
                '}';
    }
}