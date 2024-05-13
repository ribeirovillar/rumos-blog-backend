package com.blog.domain.services.validations.comment;

import com.blog.data.models.Comment;
import com.blog.data.models.Post;
import com.blog.data.repositories.PostRepository;
import com.blog.domain.exceptions.PostNotFoundException;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.UpdateValidations;

public class CommentPostValidation implements CreateValidations<Comment>, UpdateValidations<Comment> {
    private final PostRepository postRepository;

    public CommentPostValidation(PostRepository postRepository) {
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
