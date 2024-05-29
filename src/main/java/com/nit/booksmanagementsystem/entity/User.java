package com.nit.booksmanagementsystem.entity;

/**
 * User类表示用户实体。
 */
public class User {
    int id; // 用户ID
    String username, password, phone; // 用户的用户名、密码和电话

    /**
     * 获取用户ID。
     *
     * @return 用户ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置用户ID。
     *
     * @param id 用户ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取用户用户名。
     *
     * @return 用户用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户用户名。
     *
     * @param username 用户用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取用户密码。
     *
     * @return 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码。
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户电话。
     *
     * @return 用户电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户电话。
     *
     * @param phone 用户电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 返回包含用户信息的字符串表示。
     *
     * @return 包含用户信息的字符串
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}