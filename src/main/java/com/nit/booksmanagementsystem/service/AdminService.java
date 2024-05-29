package com.nit.booksmanagementsystem.service;

import com.nit.booksmanagementsystem.dao.AdminDao;
import com.nit.booksmanagementsystem.entity.Admin;
import com.nit.booksmanagementsystem.utils.Result;

public class AdminService {
    public AdminDao adminDao = new AdminDao();

    public Admin selectByUsername(String username) {
        return adminDao.selectByUsername(username);
    }

    public Result<String> verifyLogin(Admin admin) {
        String username = admin.getUsername(),
                password = admin.getPassword();

        Admin databaseAdmin = selectByUsername(username);

        if (databaseAdmin == null) {
            return new Result<>(401, "没有该用户");
        }

        if (password.equals(databaseAdmin.getPassword())) {
            return new Result<>(200, "登录成功");
        }
        return new Result<>(401, "登录失败");
    }
    public Result<String> verifyLogin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return verifyLogin(admin);
    }
}
