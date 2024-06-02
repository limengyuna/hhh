package controller;

import dao.PostDao;
import dao.impl.PostDaoImpl;
import model.Post;
import model.User;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PostDao postDao;

    @Override
    public void init() {
        postDao = new PostDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 处理 GET 请求（如果需要）
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");

        String content = request.getParameter("content");
        String img = request.getParameter("img");
        HttpSession session = request.getSession(false);
        Integer userId = null;
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            userId = user.getUserId(); // 使用User类的getUserId()方法获取用户ID
        }

        if (userId != null) {
            // 用户已登录，可以执行接下来的操作
            Random random = new Random();
            int postId = random.nextInt(1000000);
            // 创建帖子对象
            Post newPost = new Post();
            newPost.setPostId(postId);
            newPost.setUserId(userId);
            newPost.setTitle(title);
            newPost.setContent(content);
            newPost.setImg(img);

            // 将帖子保存到数据库
            postDao.createPost(newPost);

            // 保存成功后重定向到首页或其他页面
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            // 用户未登录，重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}