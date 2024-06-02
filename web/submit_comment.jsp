<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.PostDao" %>
<%@ page import="dao.impl.PostDaoImpl" %>
<%@ page import="model.Comment" %>
<%@ page import="model.User" %>
<%@ page import="java.sql.Timestamp" %>

<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String postId = request.getParameter("postId");
    String commentContent = request.getParameter("commentContent");
    System.out.println(postId);

    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int userId = user.getUserId();

    Comment newComment = new Comment();
    newComment.setPostId(Integer.parseInt(postId));
    newComment.setContent(commentContent);
    newComment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    newComment.setAuthorId(userId);

    PostDao postDAO = new PostDaoImpl();
    postDAO.addComment(newComment);

    response.sendRedirect("post_detail.jsp?id=" + postId);
%>
