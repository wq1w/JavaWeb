<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>错误</title>
    <script type="text/javascript">
        function showError() {
            var errorMessage = "<%= request.getAttribute("errorMessage") %>";
            if (errorMessage) {
                alert(errorMessage);
            }
        }
    </script>
</head>
<body onload="showError()">
<p>借阅操作失败，请重试。</p>
<a href="index.jsp">返回首页</a>
</body>
</html>
