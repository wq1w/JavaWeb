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
/**
 * LoginServlet 处理用户的登录和注册请求
 * 主要包含以下功能:
 * 1. 处理登录页面和注册页面的请求,转发到对应的 JSP 页面。
 * 2. 处理用户注册请求,调用 UserService 类的 register 方法注册用户。
 * 3. 处理用户登录请求,根据用户身份(管理员或普通用户)调用对应的 Service 类验证登录信息。
 * 4. 登录成功后,设置当前用户的身份信息(管理员或普通用户)并将用户信息保存在 session 中。
 * 5. 根据用户身份转发到不同的页面(管理员用户管理页面或图书页面)。
 * 6. 处理用户注销请求,清除 session 中的用户信息并转发到登录页面。

 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    AdminService adminService = new AdminService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据 method 参数处理不同的 GET 请求
        String method = req.getParameter("method");
        System.out.println(method);
        switch (method) {

            case "login-page":
                // 请求转发登录页面
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
            case "register-page":
                // 请求转发到注册页面
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                break;
            case "register":
                // 处理用户注册请求,调用 UserService 的 register 方法
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String phone = req.getParameter("phone");
                req.setAttribute("data", userService.register(username, password, phone));
                // 请求转发到登录页面
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
            case "logout":
                // 处理用户注销请求,清除 session 中的用户信息
                CommonUtil.setIdentity(null);
                req.getSession().setAttribute("username", null);
                req.getSession().setAttribute("user_id", null);
                // 请求转发到登录页面
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                break;
        }
    }

    /**
     * 处理用户登录请求的 servlet 方法
     * @param req HTTP 请求对象,包含用户输入的登录信息
     * @param resp HTTP 响应对象,用于向客户端返回登录结果
     * @throws ServletException 如果在 servlet 处理过程中发生异常,则抛出此异常
     * @throws IOException 如果在输入/输出处理过程中发生异常,则抛出此异常
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户输入的登录信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String identity = req.getParameter("identity");
        // 管理员登录
        if (identity.equals("admin")) {
            // 验证管理员登录信息
            if (duplicated(req, resp, adminService.verifyLogin(username, password))) {
                return;// 如果登录失败,直接返回
            }
            // 设置当前用户的身份为管理员
            CommonUtil.setIdentity(Identity.admin);
            // 将用户名存储在 session 中
            req.getSession().setAttribute("username", username);
            // 获取管理员 ID 并存储在 CommonUtil 中
            CommonUtil.setUserId(adminService.selectByUsername(username).getId());
            // 请求转发到管理员用户管理页面
            req.getRequestDispatcher("/router?page=admin_user_management").forward(req, resp);
            return;
        }
        // 用户登录
        if (duplicated(req, resp, userService.verifyLogin(username, password))) {
            return;// 如果登录失败,直接返回
        }
        // 设置当前用户的身份为普通用户
        CommonUtil.setIdentity(Identity.user);
        // 将用户名存储在会话中
        req.getSession().setAttribute("username", username);
        // 获取用户 ID 并存储在 CommonUtil 中
        CommonUtil.setUserId(userService.selectByUsername(username).getId());
        // 请求转发到图书页面
        req.getRequestDispatcher("/router?page=book").forward(req, resp);

    }

    /**
     *  检查请求中的 stringResult2 对象是否为重复的请求,并根据结果采取相应的操作
     * @param request HttpServletRequest 对象,包含当前请求的信息
     * @param response HttpServletResponse 对象,用于向客户端发送响应
     * @param stringResult2 包含请求结果的 Result 对象
     * @return 如果请求被转发到登录页面,返回 true,否则返回 false
     * @throws ServletException 如果在转发请求时出现异常
     * @throws IOException 如果在写入响应时出现异常
     */
    private boolean duplicated(HttpServletRequest request, HttpServletResponse response, Result<String> stringResult2) throws ServletException, IOException {
        // 将 stringResult2 对象设置为请求的 "data" 属性
        request.setAttribute("data", stringResult2);
        // 检查 stringResult2 对象中响应码
        if (stringResult2.getCode() != 200) {
            // 如果不成功,将请求转发到 "/login.jsp" 页面
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request, response);
            return true;
        }
        // 如果成功,设置登录状态为 true
        CommonUtil.setIsLogin(true);
        return false;
    }

}
