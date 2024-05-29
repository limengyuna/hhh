package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(password);  // In a real application, hash the password

        UserDao userDao = new UserDaoImpl(); // 使用UserDaoImpl实现类
        boolean success = userDao.registerUser(user);

        if (success) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("register.jsp?error=1");
        }
    }
}
