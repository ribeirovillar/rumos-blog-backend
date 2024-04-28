package com.blog.domain.services.validations.comment;

import com.blog.data.models.PostComment;
import com.blog.data.repositories.PostRepository;
import com.blog.domain.exceptions.PostNotFoundException;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.UpdateValidations;

import java.util.UUID;

public class CommentPostValidation implements CreateValidations<PostComment>, UpdateValidations<PostComment> {
    private final PostRepository postRepository;

    public CommentPostValidation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void validate(PostComment postComment) {
        UUID postId = postComment.getPostId();
        if (postId == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if (!postRepository.existsById(postComment.getId())) {
            throw new PostNotFoundException(postComment.getId());
        }
    }
}
