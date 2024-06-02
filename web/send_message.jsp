<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send Message</title>
    <link rel="stylesheet" href="css/styles.css">
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
    <h2>发送私信</h2>
    <form action="sendMessage" method="POST">
        <input type="hidden" name="recipientId" value="<%= request.getParameter("recipientId") %>">
        <textarea name="content" rows="4" cols="50" required="required" placeholder="请输入你的私信内容..."></textarea>
        <br>
        <button type="submit">发送</button>
    </form>
</div>
</body>
</html>
