package com.nit.booksmanagementsystem.entity;

/**
 * Admin类表示管理员实体。
 */
public class Admin {

    int id; // 管理员ID
    String username, password, phone, address; // 管理员的用户名、密码、电话和地址

    /**
     * 获取管理员ID。
     *
     * @return 管理员ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置管理员ID。
     *
     * @param id 管理员ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取管理员用户名。
     *
     * @return 管理员用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置管理员用户名。
     *
     * @param username 管理员用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取管理员密码。
     *
     * @return 管理员密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置管理员密码。
     *
     * @param password 管理员密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取管理员电话。
     *
     * @return 管理员电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置管理员电话。
     *
     * @param phone 管理员电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取管理员地址。
     *
     * @return 管理员地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置管理员地址。
     *
     * @param address 管理员地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 返回包含管理员信息的字符串表示。
     *
     * @return 包含管理员信息的字符串
     */
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}