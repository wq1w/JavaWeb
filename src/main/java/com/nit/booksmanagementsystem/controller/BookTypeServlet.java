package com.nit.booksmanagementsystem.controller;

import com.nit.booksmanagementsystem.entity.BookType;
import com.nit.booksmanagementsystem.service.BookTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图书分类管理的Servlet控制器
 * 处理与图书分类相关的HTTP请求
 * 包含以下功能:
 * 1. 根据分类名称查询分类列表
 * 2. 更新图书分类信息
 * 3. 删除图书分类
 * 4. 添加新的图书分类
 */
@WebServlet("/bookType")
public class BookTypeServlet extends HttpServlet {
    // 注入图书分类服务类
    BookTypeService bookTypeService = new BookTypeService();

    // 处理GET请求,转发到doPost方法
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    // 处理POST请求,根据请求参数method执行不同的操作
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            case "condition":
                // 根据分类名称查询分类列表
                String name = request.getParameter("name");
                request.setAttribute("types", bookTypeService.selectByNameLike(name));
                request.getRequestDispatcher("/router?page=admin_book_type_management").forward(request, response);
                break;
            case "update":
                // 更新图书分类信息
                BookType bookType = new BookType();
                bookType.setId(Integer.parseInt(request.getParameter("id")));
                bookType.setName(request.getParameter("name"));
                bookTypeService.updateOne(bookType);
                request.getRequestDispatcher("/router?page=admin_book_type_management").forward(request, response);
                break;
            case "delete":
                // 删除图书分类
                int id = Integer.parseInt(request.getParameter("id"));
                bookTypeService.deleteOne(id);
                request.getRequestDispatcher("/router?page=admin_book_type_management").forward(request, response);
                break;
            case "add":
                // 添加新的图书分类
                name = request.getParameter("name");
                bookType = new BookType();
                bookType.setName(name);
                bookTypeService.insertOne(bookType);
                request.getRequestDispatcher("/router?page=admin_book_type_management").forward(request, response);
                break;
        }
    }
}