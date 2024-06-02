<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>个人中心</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }

        header {
            background: #333;
            color: #fff;
            padding: 10px 0;
            text-align: center;
        }

        nav ul {
            padding: 0;
            list-style: none;
        }

        nav ul li {
            display: inline;
            margin: 0 10px;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
        }

        h1 {
            margin: 20px 0;
        }

        .profile-grid {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .profile-item {
            width: 80%;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 10px 0;
        }

        .profile-item h2 {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="home.jsp" id="home-link">首页</a></li>
            <li><a href="profile.jsp" id="profile-link">个人中心</a></li>
            <li><a href="messages.jsp" id="message-link">私信</a></li>
            <li><a href="create_post.jsp" id="post-link">发布帖子</a></li>
            <li><a href="login.jsp" id="logout-link">登出</a></li>
        </ul>
    </nav>
</header>
<div class="container">
    <h1>个人中心</h1>
    <div class="profile-grid">
        <div class="profile-item">
            <h2>用户名</h2>
            <p><%= user.getUsername() %></p>
        </div>
        <div class="profile-item">
            <h2>邮箱</h2>
            <p><%= user.getEmail() %></p>
        </div>
        <div class="profile-item">
            <h2>注册时间</h2>
            <p><%= user.getCreatedAt() %></p>
        </div>
    </div>
    <div class="profile-item">
        <form action="updateProfile" method="post">
            <h2>用户名</h2>
            <input type="text" name="username" value="<%= user.getUsername() %>"> <!-- 允许修改用户名 -->
            <br>
            <h2>邮箱</h2>
            <input type="text" name="email" value="<%= user.getEmail() %>"> <!-- 允许修改邮箱信息 -->
            <br>
            <input type="submit" value="确认修改">
        </form>
    </div>
</div>
</body>
</html>