<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.utils.CommonUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
<head>
    <title>Add Book Type Page</title>
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
    // public 部分
    // 从 session 中获取当前用户的用户名
    String username = (String) request.getSession().getAttribute("username");

    // 获取当前用户的身份,可能是 "admin" 或其他自定义的身份
    String identity = CommonUtil.getIdentity();

    // 从请求属性中获取当前用户的权限列表
    // 权限列表是一个由 String[] 组成的 List,其中每个 String[] 包含两个元素:
    // 第一个元素是权限名称,第二个元素是对应的 URL
    List<String[]> permissions = (List<String[]>) request.getAttribute("permissions");

    // private 部分
    // 这里可能有一些私有的变量或方法定义,不会被暴露在页面上
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
        <!-- 非顶部导航区域 -->
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
    </div>
        <div class="main">
            <div class="main-container">
                <div class="form-box">
                    <form action="/bookType" class="clearfix" method="post">
                        <!-- 这个表单用于添加新的图书类别 -->
                        <div class="form-group">
                            <!-- 隐藏的表单字段,用于指定操作类型为"添加" -->
                            <input type="hidden" name="method" value="add">
                            <!-- 隐藏的表单字段,用于存储类别 ID (如果有的话) -->
                            <input type="hidden" name="id">
                            <label for="input-username">类别名</label>
                            <!-- 显示类别名称的输入框 -->
                            <input type="text" name="name" class="form-control col-3" placeholder="类别名" id="input-username"/>
                        </div>
                        <!-- 提交表单的按钮 -->
                        <input type="submit" value="添加" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>