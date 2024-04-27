package com.blog.data.models;

import com.blog.domain.enums.CategoryEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime created;
    @ElementCollection(targetClass = CategoryEnum.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "post_categories", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "category")
    private Set<CategoryEnum> categories;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private Set<PostComment> postComments;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Set<CategoryEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEnum> categories) {
        this.categories = categories;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<PostComment> getComments() {
        return postComments;
    }

    public void setComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(content, post.content) && Objects.equals(created, post.created) && Objects.equals(categories, post.categories) && Objects.equals(author, post.author) && Objects.equals(postComments, post.postComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, created, categories, author, postComments);
    }
}
