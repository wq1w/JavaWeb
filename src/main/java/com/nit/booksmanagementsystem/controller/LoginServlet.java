package com.nit.booksmanagementsystem.controller;

import com.nit.booksmanagementsystem.service.AdminService;
import com.nit.booksmanagementsystem.service.UserService;
import com.nit.booksmanagementsystem.utils.CommonUtil;
import com.nit.booksmanagementsystem.utils.Identity;
import com.nit.booksmanagementsystem.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    AdminService adminService = new AdminService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println(method);
        switch (method) {
            // 登录页面
            case "login-page":
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
            case "register-page":
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                break;
            case "register":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String phone = req.getParameter("phone");

                req.setAttribute("data", userService.register(username, password, phone));

                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
            case "logout":
                CommonUtil.setIdentity(null);
                req.getSession().setAttribute("username", null);
                req.getSession().setAttribute("user_id", null);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String identity = req.getParameter("identity");

        if (identity.equals("admin")) {
            if (duplicated(req, resp, adminService.verifyLogin(username, password))) {
                return;
            }
            CommonUtil.setIdentity(Identity.admin);
            req.getSession().setAttribute("username", username);
            CommonUtil.setUserId(adminService.selectByUsername(username).getId());
            req.getRequestDispatcher("/router?page=admin_user_management").forward(req, resp);
            return;
        }
        if (duplicated(req, resp, userService.verifyLogin(username, password))) {
            return;
        }
        CommonUtil.setIdentity(Identity.user);
        req.getSession().setAttribute("username", username);
        CommonUtil.setUserId(userService.selectByUsername(username).getId());
        req.getRequestDispatcher("/router?page=book").forward(req, resp);

    }

    private boolean duplicated(HttpServletRequest request, HttpServletResponse response, Result<String> stringResult2) throws ServletException, IOException {
        request.setAttribute("data", stringResult2);
        if (stringResult2.getCode() != 200) {
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request, response);
            return true;
        }
        CommonUtil.setIsLogin(true);
        return false;
    }

}
