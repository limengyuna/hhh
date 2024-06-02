package dao;


import dao.PostDao;
import model.Post;
import model.Comment;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Post;

import java.util.List;

public interface PostDao {
    List<Post> getAllPosts();
    Post getPostById(int id);
    List<Comment> getCommentsByPostId(int postId);
    User getAuthorById(int authorId);
    void addComment(Comment comment);
    void createPost(Post post);
}
