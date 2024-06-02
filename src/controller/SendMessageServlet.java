package controller;

import dao.MessageDao;
import dao.impl.MessageDaoImpl;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet {
    private MessageDao messageDao = new MessageDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        User currentUser = (User) request.getSession().getAttribute("user");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int senderId = currentUser.getUserId();
        int recipientId = Integer.parseInt(request.getParameter("recipientId"));
        String content = request.getParameter("content");
        Timestamp sentAt = new Timestamp(System.currentTimeMillis());

        Message message = new Message();
        message.setSenderId(senderId);
        message.setRecipientId(recipientId);
        message.setContent(content);
        message.setSentAt(sentAt);

        messageDao.sendMessage(message);
        response.sendRedirect("messages.jsp");
    }
}
