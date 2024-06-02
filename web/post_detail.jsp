<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="dao.PostDao" %>
<%@ page import="dao.impl.PostDaoImpl" %>
<%@ page import="model.Post" %>
<%@ page import="model.Comment" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%
    String postId = request.getParameter("id");
    PostDao postDAO = new PostDaoImpl();
    Post post = postDAO.getPostById(Integer.parseInt(postId));
    List<Comment> comments = postDAO.getCommentsByPostId(Integer.parseInt(postId));
    User author = postDAO.getAuthorById(post.getUserId());
    request.setAttribute("post", post);
    request.setAttribute("comments", comments);
    request.setAttribute("author", author);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= post.getTitle() %></title>
    <link rel="stylesheet" href="css/styles.css?v=<%= System.currentTimeMillis() %>">
    <style>
        .author-container {
            display: flex;
            align-items: center;
        }
        .message-button {
            margin-left: 10px;
            padding: 5px 10px;
            font-size: 12px;
            background-color: #007BFF; 
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .message-button:hover {
            background-color: #0056b3;
        }
        .message-button:focus {
            outline: none;
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
<div class="container1">
    <h1><%= post.getTitle() %></h1>
    <p><%= post.getContent() %></p>
    <img src="img/posts_img/<%= post.getImg() %>" alt="<%= post.getTitle() %>" />
    <p>发布时间：<%= post.getCreatedAt() %></p>
    <div class="author-container">
        <p>发起人：<%= author.getUsername() %></p>
        <form action="send_message.jsp" method="GET">
            <input type="hidden" name="recipientId" value="<%= author.getUserId() %>">
            <button type="submit" class="message-button">私信</button>
        </form>
    </div>
</div>
<div class="comments-section">
    <h2>评论</h2>
    <ul>
        <% for (Comment comment : comments) {
            User commentAuthor = postDAO.getAuthorById(comment.getAuthorId());
        %>
        <li>
            <p><strong><%= commentAuthor.getUsername() %>:</strong> <%= comment.getContent() %></p>
            <p>评论时间：<%= comment.getCreatedAt() %></p>
        </li>
        <% } %>
    </ul>
</div>
<div class="comment-form">
    <h2>添加评论</h2>
    <form action="submit_comment.jsp" method="POST">
        <input type="hidden" name="postId" value="<%= post.getPostId() %>">
        <textarea name="commentContent" rows="4" cols="50" required="required" placeholder="请输入你的评论..."></textarea>
        <br>
        <button type="submit">发表评论</button>
    </form>
</div>

</body>
</html>
