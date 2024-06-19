<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.utils.CommonUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.entity.BookType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>Update Book Type Page</title>
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

        .form-box {
            padding: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-control {
            border-radius: var(--radius);
        }

        .btn-primary {
            border-radius: var(--radius);
        }

        .form-group label {
            font-weight: bold;
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
    BookType type = (BookType) request.getAttribute("type");
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
                <div class="form-box">
                    <form action="/bookType" class="clearfix" method="post">
                        <div class="form-group">
                            <input type="hidden" name="method" value="update">
                            <input type="hidden" name="id" value="<%=type.getId()%>">
                            <label for="input-username">类别名</label>
                            <input type="text" name="name" class="form-control col-3" placeholder="类别名" id="input-username" value="<%=type.getName()%>"/>
                        </div>
                        <input type="submit" value="修改" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>