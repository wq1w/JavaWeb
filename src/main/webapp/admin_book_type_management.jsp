<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.utils.CommonUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.entity.BookType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
<head>
    <title>Books Type Management Page</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/index.css">
    <link rel="stylesheet" href="resources/css/public.css">
    <style>
        :root {
            --primary: #0F63BBFF;
            --secondary: #6c757d;
            --success: #28a745;
            --danger: #dc3545;
            --warning: #ffc107;
            --info: #17a2b8;
            --light: #f8f9fa;
            --dark: #343a40;
            --radius: 10px;
        }

        body {
            background: #f0f2f5;
            font-family: 'Arial', sans-serif;
        }

        .container-fluid {
            padding: 0;
        }

        .top-nav {
            background-color: var(--primary);
            color: white;
            padding: 10px 20px;
        }

        .top-nav-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .top-nav p {
            margin: 0;
        }

        .non-top-nav {
            display: flex;
        }

        .sidebar {
            width: 200px;
            background-color: var(--light);
            border-right: 1px solid #ddd;
            padding: 20px;
        }

        .sidebar-ul {
            list-style: none;
            padding: 0;
        }

        .sidebar-ul li {
            margin-bottom: 10px;
        }

        .sidebar-ul li a {
            text-decoration: none;
            color: var(--dark);
            display: block;
            padding: 10px;
            border-radius: var(--radius);
            transition: background-color 0.3s, color 0.3s;
        }

        .sidebar-ul li a:hover {
            background-color: var(--primary);
            color: white;
        }

        .main {
            flex-grow: 1;
            padding: 20px;
        }

        .main-container {
            background-color: white;
            border-radius: var(--radius);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .query-box {
            margin-bottom: 30px;
        }

        .query-form .form-group {
            margin: 0;
        }

        .query-form .form-control {
            border-radius: var(--radius);
        }

        .query-form .btn-primary {
            border-radius: var(--radius);
        }

        .table-box {
            overflow-x: auto;
        }

        .table-box table {
            width: 100%;
            margin: 0 auto;
            border-collapse: collapse;
        }

        .table-box th, .table-box td {
            text-align: center;
            padding: 10px;
            border: 1px solid #ddd;
        }

        .table-box th {
            background-color: var(--primary);
            color: white;
        }

        .table-box tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .table-box tr:hover {
            background-color: #f1f1f1;
        }

        .table-box .btn {
            border-radius: var(--radius);
        }
    </style>
</head>
<body>
<%
    // 从会话中获取用户名
    String username = (String) request.getSession().getAttribute("username");

    // 获取当前用户的身份
    String identity = CommonUtil.getIdentity();

    // 从请求属性中获取当前用户的权限列表
    List<String[]> permissions = (List<String[]>) request.getAttribute("permissions");

    // 从请求属性中获取图书类型列表
    List<BookType> types = (List<BookType>) request.getAttribute("types");
%>
<div class="container-fluid">
    <div class="top-nav">
        <div class="top-nav-container">
            <div class="left-box">
                <p>图书管理系统</p>
            </div>
            <div class="right-box">
                <p class="top-nav-username" style="margin-right: 10px">用户：<%=username%>
                </p>
                <p class="top-nav-identity">类型：<%=identity.equals("user") ? "用户" : "管理员"%>
                </p>
            </div>
        </div>
    </div>
    <div class="non-top-nav">
        <div class="sidebar">
            <!-- 侧边栏菜单 -->
            <ul class="sidebar-ul">
                <!-- 遍历权限列表,为每个权限生成一个菜单项 -->
                <c:forEach var="item" items="<%=permissions%>">
                    <li>
                        <!-- 每个菜单项包含一个链接,链接地址从权限列表中获取 -->
                        <a href="${item[1]}">${item[0]}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="main">
            <div class="main-container">
                <div class="query-box">
                    <form action="/bookType" class="query-form clearfix">
                        <!-- 隐藏的输入字段,用于指定请求方法为 condition -->
                        <input type="hidden" name="method" value="condition">

                        <div class="form-group row" style="margin: 0">
                            <!-- 类别名输入框 -->
                            <input type="text" name="name" class="form-control col-3" placeholder="类别名">

                            <!-- 查询提交按钮 -->
                            <input type="submit" value="查询" class="btn btn-primary col-1 offset-1">
                        </div>
                    </form>
                </div>
                <div class="table-box">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>类别ID</th>
                            <th>类别名</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="type" items="<%=types%>">
                            <tr>
                                <td>${type.id}</td>
                                <td>${type.name}</td>
                                <td>
                                    <!-- 删除图书类型链接 -->
                                    <a href="/bookType?method=delete&id=${type.id}">
                                        <button class="btn btn-danger btn-sm">删除</button>
                                    </a>

                                    <!-- 修改图书类型链接 -->
                                    <a href="/router?page=admin_book_type_update&id=${type.id}">
                                        <button class="btn btn-primary btn-sm">修改</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>