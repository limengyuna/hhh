package controller;


import dao.PostDao;
import dao.impl.PostDaoImpl;
import model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        List<Post> posts = postDao.getAllPosts();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
