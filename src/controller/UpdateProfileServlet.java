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

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String newUsername = request.getParameter("username");
        String newEmail = request.getParameter("email");
        User user = (User) request.getSession().getAttribute("user");


        user.setUsername(newUsername);
        user.setEmail(newEmail);


        boolean success = updateUser(user);

        if (success) {

            request.setAttribute("message", "修改成功");
        } else {
            // 更新失败的逻辑
            // 可以添加一条失败的消息，告诉用户信息更新失败
            request.setAttribute("error", "修改失败");
        }

        // 重定向回个人中心页面
        response.sendRedirect("profile.jsp");
    }


    private boolean updateUser(User user) {

        return userDao.updateUser(user);
    }
}
