package dao.impl;

import dao.PostDao;
import model.Post;
import util.DbUtil;
import model.Comment;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {


    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT post_id, user_id, title, content, created_at, img FROM posts";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();
            if (conn != null) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    Post post = new Post();
                    post.setPostId(rs.getInt("post_id"));
                    post.setUserId(rs.getInt("user_id"));
                    post.setTitle(rs.getString("title"));
                    post.setContent(rs.getString("content"));
                    post.setCreatedAt(rs.getTimestamp("created_at"));
                    post.setImg(rs.getString("img"));
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.release(rs, stmt, conn);
        }

        return posts;
    }

    @Override
    public Post getPostById(int id) {
        Post post = null;
        String sql = "SELECT post_id, user_id, title, content, created_at, img FROM posts WHERE post_id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    post = new Post();
                    post.setPostId(rs.getInt("post_id"));
                    post.setUserId(rs.getInt("user_id"));
                    post.setTitle(rs.getString("title"));
                    post.setContent(rs.getString("content"));
                    post.setCreatedAt(rs.getTimestamp("created_at"));
                    post.setImg(rs.getString("img"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }
    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comments WHERE post_id = ?")) {
            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("comment_id"));
                comment.setPostId(rs.getInt("post_id"));
                comment.setContent(rs.getString("content"));
                comment.setCreatedAt(rs.getTimestamp("created_at"));
                comment.setAuthorId(rs.getInt("user_id"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public User getAuthorById(int authorId) {
        User user = null;
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?")) {
            stmt.setInt(1, authorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public void addComment(Comment comment) {
        try (Connection conn =  DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO comments (post_id, content, created_at, user_id )VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, comment.getPostId());
            stmt.setString(2, comment.getContent());
            stmt.setTimestamp(3, comment.getCreatedAt());
            stmt.setInt(4, comment.getAuthorId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createPost(Post post) {
        String sql = "INSERT INTO posts (post_id, user_id, title, content, created_at, img) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, post.getPostId());
            statement.setInt(2, post.getUserId());
            statement.setString(3, post.getTitle());
            statement.setString(4, post.getContent());
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            statement.setString(6, post.getImg());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

