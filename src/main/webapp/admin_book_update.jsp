<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nit.booksmanagementsystem.vo.BorrowingVo" %>
<%@ page import="com.nit.booksmanagementsystem.utils.CommonUtil" %>
<%@ page import="com.nit.booksmanagementsystem.entity.Book" %>
<%@ page import="com.nit.booksmanagementsystem.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>Update Book Page</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/index.css">
    <link rel="stylesheet" href="/resources/css/public.css">
    <style>
        .form-box {

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
    Book book = (Book) request.getAttribute("book");
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
            <ul class="sidebar-ul">
                <c:forEach var="item" items="<%=permissions%>">
                    <li><a href="${item[1]}">${item[0]}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="main">
            <div class="main-container">
                <div class="form-box">
                    <form action="/book" class="clearfix" method="post">
                        <div class="form-group">
                            <input type="hidden" name="method" value="update">
                            <input type="hidden" name="id" value="<%=book.getId()%>">
                            <label for="input-username">图书名称</label>
                            <input type="text"
                                   name="name"
                                   class="form-control col-3"
                                   placeholder="图书名称"
                                   id="input-username"
                                   value="<%=book.getName()%>"/>
                        </div>
                        <div class="form-group">
                            <label for="input-password">作者</label>
                            <input type="text"
                                   name="author"
                                   class="form-control col-3"
                                   placeholder="作者"
                                   id="input-password"
                                   value="<%=book.getAuthor()%>"/>
                        </div>
                        <div class="form-group">
                            <label for="input-phone">出版社</label>
                            <input type="text"
                                   name="publisher"
                                   class="form-control col-3"
                                   placeholder="出版社"
                                   id="input-phone"
                                   value="<%=book.getPublisher()%>"/>
                        </div>
                        <div class="form-group">
                            <label for="input-d1">ISBN</label>
                            <input type="text"
                                   name="isbn"
                                   class="form-control col-3"
                                   placeholder="ISBN"
                                   id="input-d1"
                                   value="<%=book.getIsbn()%>"/>
                        </div>
                        <div class="form-group">
                            <label for="input-d2">描述</label>
                            <textarea class="form-control" id="input-d2" rows="3" placeholder="描述" name="info">
                                <%=book.getInfo()%>
                            </textarea>
                        </div>
                        <div class="form-group">
                            <label for="input-d3">定价</label>
                            <input type="text"
                                   name="pricing"
                                   class="form-control col-3"
                                   placeholder="定价"
                                   id="input-d3"
                                   value="<%=book.getPricing()%>"/>
                        </div>
                        <div class="form-group">
                            <label style="">是否被借阅</label>

                            <div style="display: flex; width: 260px; line-height: 38px">
                                <div style="display: flex; width: 130px">
                                    <p>是</p>
                                    <input type="radio"
                                           name="is_borrowed"
                                           class="form-control col-3"
                                           placeholder="是否被借阅"
                                           checked="<%=book.getIsBorrowed()%>"
                                           value="是"
                                           style="transform: scale(.5)"/>
                                </div>
                                <div style="display: flex; width: 130px">
                                    <p>否</p>
                                    <input type="radio"
                                           name="is_borrowed"
                                           class="form-control col-3"
                                           placeholder="是否被借阅"
                                           checked="<%=book.getIsBorrowed()%>"
                                           value="否"
                                           style="transform: scale(.5)"/>
                                </div>
                            </div>
                        </div>
                        <input type="submit" value="修改" class="btn btn-primary col-1">
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
