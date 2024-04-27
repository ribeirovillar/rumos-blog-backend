package com.blog.domain.services.validations.comment;

import com.blog.data.models.Comment;
import com.blog.data.models.Post;
import com.blog.data.repositories.PostRepository;
import com.blog.domain.exceptions.PostNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PostValidation implements CommentValidations {
    private final PostRepository postRepository;

    public PostValidation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(Comment comment) {
        Post post = comment.getPost();
        if (post == null || post.getId() == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if (!postRepository.existsById(post.getId())) {
            throw new PostNotFoundException(post.getId());
        }
    }
}
