package com.nit.booksmanagementsystem.service;

import java.util.List;

import com.nit.booksmanagementsystem.dao.UserDao;
import com.nit.booksmanagementsystem.entity.User;
import com.nit.booksmanagementsystem.utils.Result;

public class UserService {

    public UserDao userDao = new UserDao();

    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    public Result<String> verifyLogin(User user) {
        String username = user.getUsername(),
                password = user.getPassword();

        User databaseUser = selectByUsername(username);

        if (databaseUser == null) {
            return new Result<>(401, "没有该用户");
        }

        if (password.equals(databaseUser.getPassword())) {
            return new Result<>(200, "登录成功");
        }
        return new Result<>(401, "登录失败");
    }

    public Result<String> verifyLogin(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return verifyLogin(user);
    }

    public Result<String> register(String username, String password, String phone) {

        if (selectByUsername(username) != null) {
            return new Result<>(403, "注册失败");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        userDao.insertOne(user);
        return new Result<>(200, "注册成功");
    }

    public List<User> selectAll() {
        return userDao.selectAll();
    }

    public void deleteOne(int id) {
        userDao.deleteOne(id);
    }

    public List<User> selectByUsernameLike(String username) {
        return userDao.selectByUsernameLike(username);
    }

    public User selectOne(int id) {
        return userDao.selectOne(id);
    }

    public void updateOne(User user) {
        if (selectByUsername(user.getUsername()) != null) {
            throw new RuntimeException("重复用户名，修改失败");
        }
        userDao.updateOne(user);
    }

}
