package dao;


import model.Message;
import java.util.List;

public interface MessageDao {
    void sendMessage(Message message);
    List<Message> getMessagesByUserId(int userId);

}
