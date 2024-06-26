<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.utils.CommonUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
<head>
    <title>Add Book Page</title>
    <script>
        function validateForm() {
            // 获取表单元素
            var name = document.forms["bookForm"]["name"].value;
            var author = document.forms["bookForm"]["author"].value;
            var publisher = document.forms["bookForm"]["publisher"].value;
            var isbn = document.forms["bookForm"]["isbn"].value;
            var info = document.forms["bookForm"]["info"].value;
            var pricing = document.forms["bookForm"]["pricing"].value;
            var isBorrowed = document.forms["bookForm"]["is_borrowed"].value;

            // 检查是否有任一数据为空
            if (name.trim() === "" || author.trim() === "" || publisher.trim() === "" ||
                isbn.trim() === "" || info.trim() === "" || pricing.trim() === "" || isBorrowed.trim() === "") {
                alert("所有字段都是必填的。");
                return false;
            }

            // 检查定价是否为有效的数字
            if (isNaN(pricing)) {
                alert("定价必须是一个有效的数字。");
                return false;
            }

            return true;
        }
    </script>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/index.css">
    <link rel="stylesheet" href="/resources/css/public.css">
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

        .radio-group {
            display: flex;
            align-items: center;
        }

        .radio-group p {
            margin: 0 10px 0 0;
        }

        .radio-group input[type="radio"] {
            margin: 0 10px 0 0;
            transform: scale(1.2);
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
                    <form name="bookForm" action="/book" class="clearfix" method="post" onsubmit="return validateForm()">
                        <div class="form-group">
                            <input type="hidden" name="method" value="add">
                            <label for="input-username">图书名称</label>
                            <input type="text" name="name" class="form-control col-3" placeholder="图书名称" id="input-username"/>
                        </div>
                        <div class="form-group">
                            <label for="input-author">作者</label>
                            <input type="text" name="author" class="form-control col-3" placeholder="作者" id="input-author"/>
                        </div>
                        <div class="form-group">
                            <label for="input-publisher">出版社</label>
                            <input type="text" name="publisher" class="form-control col-3" placeholder="出版社" id="input-publisher"/>
                        </div>
                        <div class="form-group">
                            <label for="input-isbn">ISBN</label>
                            <input type="text" name="isbn" class="form-control col-3" placeholder="ISBN" id="input-isbn"/>
                        </div>
                        <div class="form-group">
                            <label for="input-info">描述</label>
                            <textarea class="form-control col-3" id="input-info" rows="3" placeholder="描述" name="info"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="input-pricing">定价</label>
                            <input type="text" name="pricing" class="form-control col-3" placeholder="定价" id="input-pricing"/>
                        </div>
                        <div class="form-group">
                            <label>是否被借阅</label>
                            <div class="radio-group">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="is_borrowed" id="borrowed-yes" value="是">
                                    <label class="form-check-label" for="borrowed-yes">是</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="is_borrowed" id="borrowed-no" value="否" checked>
                                    <label class="form-check-label" for="borrowed-no">否</label>
                                </div>
                            </div>
                        </div>
                        <input type="submit" value="添加" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>