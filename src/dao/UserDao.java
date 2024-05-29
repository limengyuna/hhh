package dao;

import model.User;

public interface UserDao {
    boolean registerUser(User user);
    User loginUser(String username, String passwordHash);
}
