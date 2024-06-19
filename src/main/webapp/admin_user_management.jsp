<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.nit.booksmanagementsystem.utils.CommonUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>Users Management Page</title>
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
    // public
    String username = (String) request.getSession().getAttribute("username");
    String identity = CommonUtil.getIdentity();
    List<String[]> permissions = (List<String[]>) request.getAttribute("permissions");
    // private
    List<User> users = (List<User>) request.getAttribute("users");
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
            <div class="main-container">
                <div class="query-box">
                    <form action="/user" class="query-form clearfix">
                        <div class="form-group row">
                            <input type="hidden" name="method" value="condition">
                            <input type="text" name="username" class="form-control col-3" placeholder="用户名"/>
                            <input type="submit" value="查询" class="btn btn-primary col-1 offset-1">
                        </div>
                    </form>
                </div>
                <div class="table-box">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>用户ID</th>
                            <th>用户名</th>
                            <th>手机号</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="<%=users%>">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>${user.phone}</td>
                                <td>
                                    <a href="/user?method=delete&id=${user.id}">
                                        <button class="btn btn-danger btn-sm">删除</button>
                                    </a>
                                    <a href="/router?page=admin_user_update&id=${user.id}">
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