package model;


import java.sql.Timestamp;

public class Comment {
    private int id;
    private int postId;
    private String content;
    private Timestamp createdAt;
    private int authorId;

    // Constructors
    public Comment() {
    }

    public Comment(int id, int postId, String content, Timestamp createdAt, int authorId) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
        this.authorId = authorId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
