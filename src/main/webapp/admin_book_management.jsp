<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.utils.CommonUtil" %>
<%@ page import="com.nit.booksmanagementsystem.entity.Book" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
<head>
    <title>Books Page</title>
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

        .book-page {
            padding: 20px;
            background-color: white;
            border-radius: var(--radius);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .query-form {
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

        .books-table {
            overflow-x: auto;
        }

        .books-table table {
            width: 100%;
            margin: 0 auto;
            border-collapse: collapse;
        }

        .books-table th, .books-table td {
            text-align: center;
            padding: 10px;
            border: 1px solid #ddd;
        }

        .books-table th {
            background-color: var(--primary);
            color: white;
        }

        .books-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .books-table tr:hover {
            background-color: #f1f1f1;
        }

        .books-table .btn {
            border-radius: var(--radius);
        }
    </style>
</head>
<body>
<%
    // public
    String username = (String) request.getSession().getAttribute("username");
    String identity = CommonUtil.getIdentity();
    List<String[]> permissions = (List<String[]>) request.getAttribute("permissions");
    // private
    List<Book> books = (List<Book>) request.getAttribute("books");
%>
<div class="container-fluid">
    <div class="top-nav">
        <div class="top-nav-container">
            <div class="left-box">
                <p>图书管理系统</p>
            </div>
            <div class="right-box">
                <p class="top-nav-username" style="margin-right: 10px">用户：<%=username%></p>
                <p class="top-nav-identity">类型：<%=identity.equals("user") ? "用户" : "管理员"%></p>
            </div>
        </div>
    </div>
    <div class="non-top-nav">
        <div class="sidebar">
            <ul class="sidebar-ul">
                <c:forEach var="item" items="<%=permissions%>">
                    <li><a href="${item[1]}">${item[0]}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="main">
            <div class="book-page">
                <div class="query-form">
                    <form action="/book" method="post" class="clearfix" style="width: 90%; margin: 0 auto 30px;">
                        <div class="form-group row">
                            <input type="hidden" name="method" value="condition">
                            <input type="text" name="author" class="form-control col-3" placeholder="作者"/>
                            <input type="text" name="name" class="form-control col-3 offset-1" placeholder="书名"/>
                            <input type="submit" value="查询" class="btn btn-primary col-1 offset-4">
                        </div>
                    </form>
                </div>
                <div class="books-table">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>图书ID</th>
                            <th>图书名称</th>
                            <th>图书作者</th>
                            <th>出版社</th>
                            <th>ISBN</th>
                            <th>图书描述</th>
                            <th>定价</th>
                            <th>是否被借阅</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="book" items="<%=books%>">
                            <tr>
                                <td>${book.id}</td>
                                <td>${book.name}</td>
                                <td>${book.author}</td>
                                <td>${book.publisher}</td>
                                <td>${book.isbn}</td>
                                <td>${book.info}</td>
                                <td>${book.pricing}</td>
                                <td>${book.isBorrowed}</td>
                                <td>
                                    <a href="/book?method=delete&id=${book.id}">
                                        <button class="btn btn-danger btn-sm">删除</button>
                                    </a>
                                    <a href="/router?page=admin_book_update&id=${book.id}">
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