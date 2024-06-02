<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.MessageDao" %>
<%@ page import="dao.impl.MessageDaoImpl" %>
<%@ page import="model.Message" %>
<%@ page import="model.User" %>
<%

    User currentUser = (User) session.getAttribute("user");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int currentUserId = currentUser.getUserId();
    MessageDao messageDao = new MessageDaoImpl();
    List<Message> messages = messageDao.getMessagesByUserId(currentUserId);
    request.setAttribute("messages", messages);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>私信 - 找搭子系统</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="index.jsp" id="home-link">首页</a></li>
            <li><a href="profile.jsp" id="profile-link">个人中心</a></li>
            <li><a href="messages.jsp" id="message-link">私信</a></li>
            <li><a href="create_post.jsp" id="post-link">发布帖子</a></li>
            <li><a href="login.jsp" id="logout-link">登出</a></li>
        </ul>
    </nav>
</header>
<div class="container">
    <h1>我的私信</h1>
    <div id="messages">
        <%
            messages = (List<Message>) request.getAttribute("messages");
            for (Message message : messages) {
                boolean isSender = message.getSenderId() == currentUserId;
                String userLabel = isSender ? "发送给" : "来自";
                int otherUserId = isSender ? message.getRecipientId() : message.getSenderId();
        %>
        <div class="message">
            <p><strong><%= userLabel %>: 用户ID <%= otherUserId %></strong></p>
            <p><%= message.getContent() %></p>
            <p>发送时间：<%= message.getSentAt() %></p>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
