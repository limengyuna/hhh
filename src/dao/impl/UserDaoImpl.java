package dao.impl;


import dao.UserDao;
import model.User;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl() {
        this.connection = DbUtil.getConnection();
    }

    @Override
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User loginUser(String username, String passwordHash) {
        String sql = "SELECT * FROM users WHERE user_id= ? AND password_hash = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPasswordHash(resultSet.getString("password_hash"));
                    user.setCreatedAt(resultSet.getString("created_at"));
                    return user;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        // 更新用户信息的 SQL 语句
        String sql = "UPDATE users SET username = ?, email = ? WHERE user_id = ?";

        try (
                Connection connection = DbUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            // 设置 SQL 语句的参数
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getUserId());

            // 执行 SQL 更新语句
            int rowsUpdated = statement.executeUpdate();

            // 如果更新成功，返回 true；否则返回 false
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // 更新过程中出现异常，返回 false
            return false;
        }
    }
}
