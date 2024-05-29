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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDao postDao = new PostDaoImpl();
        List<Post> postList = postDao.getAllPosts();

        request.setAttribute("postList", postList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
