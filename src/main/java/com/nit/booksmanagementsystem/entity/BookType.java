package com.nit.booksmanagementsystem.entity;

/**
 * 图书分类实体类
 * 用于表示图书的分类信息
 * 包含两个属性:
 * 1. id: id
 * 2. name: 分类名称
 */
public class BookType {
    // id
    int id;
    // 分类名称
    String name;

    // id的getter和setter方法
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // name的getter和setter方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // 重写toString()方法,用于打印对象信息
    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}