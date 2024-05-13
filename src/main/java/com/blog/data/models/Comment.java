package com.blog.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "post_comments")
public class Comment extends BaseEntity implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    private User author;
    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    @JsonBackReference
    private Post post;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
}
