package com.blog.domain.services.validations.comment;

import com.blog.data.models.PostComment;
import com.blog.data.models.Post;
import com.blog.data.repositories.PostRepository;
import com.blog.domain.exceptions.PostNotFoundException;
import org.springframework.stereotype.Component;

public class PostValidation implements CommentValidations {
    private final PostRepository postRepository;

    public PostValidation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(PostComment postComment) {
        /*Post post = postComment.getPost();
        if (post == null || post.getId() == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if (!postRepository.existsById(post.getId())) {
            throw new PostNotFoundException(post.getId());
        }*/
    }
}
