package com.nit.booksmanagementsystem.controller;

import com.nit.booksmanagementsystem.entity.Book;
import com.nit.booksmanagementsystem.entity.BookType;
import com.nit.booksmanagementsystem.entity.User;
import com.nit.booksmanagementsystem.service.BookService;
import com.nit.booksmanagementsystem.service.BookTypeService;
import com.nit.booksmanagementsystem.service.BorrowingService;
import com.nit.booksmanagementsystem.service.UserService;
import com.nit.booksmanagementsystem.utils.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图书管理系统的 RouterServlet 类
 * 负责处理客户端的HTTP请求,并转发到相应的JSP页面
 */
@WebServlet("/router")
public class RouterServlet extends HttpServlet {
    BookService bookService = new BookService();
    BorrowingService borrowingService = new BorrowingService();
    UserService userService = new UserService();
    BookTypeService bookTypeService = new BookTypeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 处理GET请求,根据请求参数page的值转发到不同的JSP页面
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数page的值
        String page = request.getParameter("page");

        // 设置用户权限信息到请求属性中
        request.setAttribute("permissions", CommonUtil.permissions.get(CommonUtil.getIdentity()));
        // 根据page的值转发到不同的JSP页面
        switch (page) {
            // /router?page=book
            case "book":
                // 如果请求属性中没有books,则从BookService中获取所有图书并设置到请求属性中
                if (request.getAttribute("books") == null) {
                    request.setAttribute("books", bookService.selectAll());
                }
                request.getRequestDispatcher("admin_book_management.jsp").forward(request, response);
                break;
            case "borrowing":
                // 根据用户身份(admin或当前用户)获取所有借阅信息,并设置到请求属性中
                request.setAttribute("borrowings", CommonUtil.getIdentity().equals("admin") ? borrowingService.selectVoAll() : borrowingService.selectVoByCurrentUser());
                request.getRequestDispatcher("borrowing.jsp").forward(request, response);
                break;
            case "user_profile":
                // 请求转发到 "user_profile.jsp" 页面
                request.getRequestDispatcher("user_profile.jsp").forward(request, response);
                break;
            case "admin_user_management":
                // 管理员用户管理页面
                if (request.getAttribute("users") == null) {
                    request.setAttribute("users", userService.selectAll());
                }
                // 请求转发到 "admin_user_management.jsp" 页面
                request.getRequestDispatcher("admin_user_management.jsp").forward(request, response);
                break;
            case "admin_add_user":
                // 请求转发到 "admin_add_user.jsp" 页面
                request.getRequestDispatcher("admin_add_user.jsp").forward(request, response);
                break;
            case "admin_user_update":
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.selectOne(id);
                // 找到要更新的 user 设置到 request 中
                request.setAttribute("user", user);
                // 请求转发到 "admin_user_update.jsp" 页面
                request.getRequestDispatcher("admin_user_update.jsp").forward(request, response);
                break;
            case "admin_book_type_management":
                // 管理员图书类型管理
                if (request.getAttribute("types") == null) {
                    request.setAttribute("types", bookTypeService.selectAll());
                }
                // 请求转发到 "admin_book_type_management.jsp" 页面
                request.getRequestDispatcher("admin_book_type_management.jsp").forward(request, response);
                break;
            case "admin_book_type_update":
                // 管理员图书类型修改
                id = Integer.parseInt(request.getParameter("id"));
                BookType bookType = bookTypeService.selectOne(id);
                request.setAttribute("type", bookType);
                request.getRequestDispatcher("admin_book_type_update.jsp").forward(request, response);
            case "admin_book_type_insert":
                // 管理员图书类型添加
                request.getRequestDispatcher("admin_add_book_type.jsp").forward(request, response);
                break;
            case "admin_book_management":
                // 管理员图书管理
                if (request.getAttribute("books") == null) {
                    request.setAttribute("books", bookService.selectAll());
                }
                request.getRequestDispatcher("admin_book_management.jsp").forward(request, response);
                break;
            case "admin_add_book":
                // 管理员添加图书
                request.getRequestDispatcher("admin_add_book.jsp").forward(request, response);
                break;
            case "admin_book_update":
                // 管理员更新图书
                id = Integer.parseInt(request.getParameter("id"));
                Book book = bookService.selectOne(id);
                request.setAttribute("book", book);
                request.getRequestDispatcher("admin_book_update.jsp").forward(request, response);
                break;
        }
    }
}
