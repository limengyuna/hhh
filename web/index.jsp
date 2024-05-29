<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册 - 找搭子系统</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h1>注册</h1>
    <form action="register" method="post">
        <input type="text" name="username" placeholder="用户名" required>
        <input type="email" name="email" placeholder="电子邮件" required>
        <input type="password" name="password" placeholder="密码" required>
        <button type="submit">注册</button>
    </form>
    <p>已有账号？ <a href="login.jsp">登录</a></p>
</div>
</body>
</html>
