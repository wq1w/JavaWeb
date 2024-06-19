package com.nit.booksmanagementsystem.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nit.booksmanagementsystem.entity.User;
import com.nit.booksmanagementsystem.utils.JdbcUtil;

public class UserDao {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User selectByUsername(String username) {
        String sql = "select * from `user` where username = ?";

        ResultSet resultSet = JdbcUtil.query(sql, username);
        List<User> users = resultSetToUserList(resultSet);
        return (users.size() > 0) ? users.get(0) : null;
    }

    /**
     * 添加新用户
     *
     * @param user 要添加的用户对象
     */
    public void insertOne(User user) {
        String sql = "INSERT INTO `user` (username, `password`, phone) VALUES (?, ?, ?)";
        // 执行插入语句
        JdbcUtil.insert(sql, true, user.getUsername(), user.getPassword(), user.getPhone());
    }

    /**
     * 将 ResultSet 转换为 User 对象列表
     *
     * @param resultSet 要转换的结果集
     * @return User 对象列表
     */
    private List<User> resultSetToUserList(ResultSet resultSet) {
        try {
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setPhone(resultSet.getString(4));
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找所有用户
     *
     * @return 所有用户对象列表
     */
    public List<User> selectAll() {
        String sql = "select * from `user`";
        ResultSet resultSet = JdbcUtil.query(sql);
        return resultSetToUserList(resultSet);
    }

    /**
     * 根据用户 ID 删除用户
     *
     * @param id 要删除的用户 ID
     */
    public void deleteOne(int id) {
        String sql = "delete from `user` where id = ?";
        JdbcUtil.execute(sql, id);
    }

    /**
     * 根据模糊的用户名查找用户
     *
     * @param username 模糊的用户名
     * @return 找到的用户对象列表
     */
    public List<User> selectByUsernameLike(String username) {
        String sql = "select * from `user` where username like '%" + username + "%'";
        ResultSet resultSet = JdbcUtil.query(sql);
        return resultSetToUserList(resultSet);
    }

    /**
     * 根据用户 ID 查找用户
     *
     * @param id 要查找的用户 ID
     * @return 找到的用户对象
     */
    public User selectOne(int id) {
        String sql = "select * from `user` where `id` = ?";
        ResultSet resultSet = JdbcUtil.query(sql, id);
        return resultSetToUserList(resultSet).get(0);
    }

    /**
     * 更新用户信息
     *
     * @param user 要更新的用户对象
     */
    public void updateOne(User user) {
        String sql = "update `user` set `username`=?, password=?, phone=? WHERE id=?";
        JdbcUtil.execute(sql, user.getUsername(), user.getPassword(), user.getPhone(), user.getId());
    }
}
