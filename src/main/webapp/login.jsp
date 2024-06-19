<%@ page import="com.nit.booksmanagementsystem.utils.Result" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/index.css">
    <style>
        :root {
            --primary: #0F63BBFF;
            --purple: #6f42c1;
            --radius: 10px;
        }

        body {
            background: #f0f2f5;
            font-family: 'Arial', sans-serif;
        }

        .login-container {
            height: 100vh;
            background: url(resources/img/bg1.jpg) no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-box {
            background-color: rgba(255, 255, 255, 0.9);
            width: 400px;
            padding: 30px;
            border-radius: var(--radius);
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            text-align: center;
            animation: fadeIn 1s ease-in-out;
        }

        .login-box-title {
            margin-bottom: 20px;
            font-size: 24px;
            color: var(--primary);
        }

        .login-box-form .form-group {
            margin-bottom: 15px;
        }

        .login-box-form label {
            font-weight: bold;
        }

        .login-box-form .form-control {
            border-radius: var(--radius);
        }

        .btn-primary {
            width: 100%;
            border-radius: var(--radius);
            background-color: var(--primary);
            border-color: var(--primary);
        }

        .btn-primary:hover {
            background-color: darken(var(--primary), 10%);
            border-color: darken(var(--primary), 10%);
        }

        .login-box-register {
            margin-top: 20px;
        }

        .login-box-register a {
            color: var(--purple);
            text-decoration: none;
        }

        .login-box-register a:hover {
            text-decoration: underline;
        }
        .select-input {
            font-family: 'Courier New', Courier, monospace;
            font-size: 16px;
            color: #333;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: var(--radius);
            padding: 10px;
            transition: background-color 0.3s, border-color 0.3s;
        }

        .select-input:focus {
            background-color: #fff;
            border-color: var(--primary);
            outline: none;
            box-shadow: 0 0 5px rgba(15, 99, 187, 0.3);
        }
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>
<%
    Result<String> result = (Result) request.getAttribute("data");
%>
<script lang="javascript">
    const e = <%=result == null ? false : result.getCode()%>;
    if (e) {
        alert("<%=(result == null) ? "" : result.getData()%>");
    }
</script>
<div class="container-fluid login-container">
    <div class="login-box">
        <h3 class="login-box-title">图书管理系统</h3>
        <form action="/login" method="post" class="clearfix">
            <div class="login-box-form">
                <div class="form-group row">
                    <label class="col-3">用户名</label>
                    <input type="text" class="form-control col-8" name="username" placeholder="请输入用户名" required>
                </div>
                <div class="form-group row">
                    <label class="col-3">密码</label>
                    <input type="password" class="form-control col-8" name="password" placeholder="请输入密码" required>
                </div>
                <div class="form-group row">
                    <label class="col-3">身份</label>
                    <select class="form-control col-8 select-input" name="identity" required>
                        <option value="user">用户</option>
                        <option value="admin">管理员</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">登录</button>
            </div>
        </form>
        <div class="login-box-register">
            <a href="/login?method=register-page">用户注册</a>
        </div>
    </div>
</div>
</body>
</html>