package com.nit.booksmanagementsystem.service;

import java.util.List;

import com.nit.booksmanagementsystem.dao.UserDao;
import com.nit.booksmanagementsystem.entity.User;
import com.nit.booksmanagementsystem.utils.Result;

public class UserService {
    // 创建一个 UserDao 对象,用于操作用户数据
    public UserDao userDao = new UserDao();

    /**
     * 根据用户名查询用户
     *
     * @param username 要查询的用户名
     * @return 查询到的用户对象
     */
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    /**
     * 验证用户登录
     *
     * @param user 包含用户名和密码的用户对象
     * @return 登录结果, 包含状态码和状态消息
     */
    public Result<String> verifyLogin(User user) {
        String username = user.getUsername(),
                password = user.getPassword();
        // 根据用户名查询数据库中的用户
        User databaseUser = selectByUsername(username);
        // 如果没有查询到用户,返回登录失败状态
        if (databaseUser == null) {
            return new Result<>(401, "没有该用户");
        }
        // 如果密码匹配,返回登录成功状态
        if (password.equals(databaseUser.getPassword())) {
            return new Result<>(200, "登录成功");
        }
        // 如果密码不匹配,返回登录失败状态
        return new Result<>(401, "登录失败");
    }

    /**
     * 验证用户登录,使用用户名和密码作为参数
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果, 包含状态码和状态消息
     */
    public Result<String> verifyLogin(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return verifyLogin(user);
    }

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @param phone    手机号
     * @return 注册结果, 包含状态码和状态消息
     */
    public Result<String> register(String username, String password, String phone) {
        // 检查用户名是否已经存在
        if (selectByUsername(username) != null) {
            return new Result<>(403, "注册失败");
        }
        // 创建新用户并插入数据库
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        userDao.insertOne(user);
        return new Result<>(200, "注册成功");
    }

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    /**
     * 根据 ID 删除用户
     *
     * @param id 要删除的用户 ID
     */
    public void deleteOne(int id) {
        userDao.deleteOne(id);
    }

    /**
     * 根据用户名模糊查询用户
     *
     * @param username 要查询的用户名关键字
     * @return 查询到的用户列表
     */
    public List<User> selectByUsernameLike(String username) {
        return userDao.selectByUsernameLike(username);
    }

    /**
     * 根据 ID 查询单个用户
     *
     * @param id 要查询的用户 ID
     * @return 查询到的用户对象
     */
    public User selectOne(int id) {
        return userDao.selectOne(id);
    }

    /**
     * 更新用户信息
     *
     * @param user 要更新的用户对象
     */
    public void updateOne(User user) {
        // 更新用户信息
        userDao.updateOne(user);
    }

}
