<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.PostDao" %>
<%@ page import="dao.impl.PostDaoImpl" %>
<%@ page import="model.Post" %>
<%
    PostDao postDAO = new PostDaoImpl();
    List<Post> posts = postDAO.getAllPosts();
    request.setAttribute("posts", posts);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页 - 找搭子系统</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="#" id="home-link">首页</a></li>
            <li><a href="profile.jsp" id="profile-link">个人中心</a></li>
            <li><a href="messages.jsp" id="message-link">私信</a></li>
            <li><a href="create_post.jsp" id="post-link">发布帖子</a></li>
            <li><a href="login.jsp" id="logout-link">登出</a></li>
        </ul>
    </nav>
</header>
<div class="container">
    <h1>活动列表</h1>
    <div id="posts">
        <%
            posts = (List<Post>) request.getAttribute("posts");
            for (Post post : posts) {
        %>
<%--        <div class="post">--%>
<%--            <h2><%= post.getTitle() %></h2>--%>
<%--            <p><%= post.getContent() %></p>--%>
<%--            <img src="img/posts_img/<%= post.getImg() %>" alt="<%= post.getTitle() %>" />--%>
<%--            <p>发布时间：<%= post.getCreatedAt() %></p>--%>
<%--        </div>--%>
        <div class="post">
            <h2><a href="post_detail.jsp?id=<%= post.getPostId() %>"><%= post.getTitle() %></a></h2>
            <p><%= post.getContent() %></p>
            <img src="img/posts_img/<%= post.getImg() %>" alt="<%= post.getTitle() %>" />
            <p>发布时间：<%= post.getCreatedAt() %></p>
        </div>

        <%
            }
        %>
    </div>
</div>
</body>
</html>
