package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        String email = (String) request.getSession().getAttribute("email");
        int age = (int) request.getSession().getAttribute("age");


        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><head><title>User Profile</title></head><body>");
        response.getWriter().println("<h1>Welcome to Your Profile</h1>");
        response.getWriter().println("<p>Username: " + username + "</p>");
        response.getWriter().println("<p>Email: " + email + "</p>");
        response.getWriter().println("<p>Age: " + age + "</p>");
        response.getWriter().println("</body></html>");
    }
}
