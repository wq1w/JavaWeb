package com.nit.booksmanagementsystem.dao;

import com.nit.booksmanagementsystem.entity.Admin;
import com.nit.booksmanagementsystem.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * AdminDao 类负责与数据库中的 'admin' 表进行交互。
 * 提供方法: 根据提供的用户名检索 Admin 对象。
 */
public class AdminDao {
    /**
     * 根据提供的用户名从数据库中检索 Admin 对象
     * @param username 要检索的管理员的用户名
     * @return 如果找到则返回 Admin 对象, 否则返回 null
     */
    public Admin selectByUsername(String username) {
        // 定义 SQL 语句
        String sql = "select * from `admin` where username = ?";
        // 使用 JdbcUtil 类执行查询,并获取结果集
        ResultSet resultSet = JdbcUtil.query(sql, username);

        try {

            if (!resultSet.next()) {
                return null; // 如果没有找到结果,返回 null
            }
            // 找到结果, 创建一个新的 Admin 对象,并根据检索到的数据设置其属性
            Admin admin = new Admin();
            admin.setId(resultSet.getInt(1));
            admin.setUsername(resultSet.getString(2));
            admin.setPassword(resultSet.getString(3));
            admin.setPhone(resultSet.getString(4));
            admin.setAddress(resultSet.getString(5));
            // 返回 admin 对象
            return admin;
        } catch (SQLException e) {
            // 若异常, 抛出运行时异常
            throw new RuntimeException(e);
        }
    }
}
