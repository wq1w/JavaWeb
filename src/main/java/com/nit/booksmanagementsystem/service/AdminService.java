package com.nit.booksmanagementsystem.service;

import com.nit.booksmanagementsystem.dao.AdminDao;
import com.nit.booksmanagementsystem.entity.Admin;
import com.nit.booksmanagementsystem.utils.Result;

public class AdminService {
    // 定义AdminDao 实体变量,用于访问数据库
    public AdminDao adminDao = new AdminDao();

    /**
     * 根据用户名查找 Admin 对象
     *
     * @param username 要查找的用户名
     * @return 查找到的对象, 如果没有则返回 null
     */
    public Admin selectByUsername(String username) {
        return adminDao.selectByUsername(username);
    }

    /**
     * 验证用户登录信息
     *
     * @param admin 包含用户名和密码的 Admin 对象
     * @return 登录验证结果, 如果成功则返回状态码 200 和"登录成功"的消息,否则返回状态码 401 和相应的错误消息
     */
    public Result<String> verifyLogin(Admin admin) {
        String username = admin.getUsername(),
                password = admin.getPassword();
        // 根据用户名查找数据库中的 Admin 对象
        Admin databaseAdmin = selectByUsername(username);
        // 如果没有找到对应的用户,返回 401 未授权错误
        if (databaseAdmin == null) {
            return new Result<>(401, "没有该用户");
        }
        // 如果密码匹配,返回 200 成功结果
        if (password.equals(databaseAdmin.getPassword())) {
            return new Result<>(200, "登录成功");
        }
        // 否则返回 401 未授权错误
        return new Result<>(401, "登录失败");
    }

    public Result<String> verifyLogin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return verifyLogin(admin);
    }
}
