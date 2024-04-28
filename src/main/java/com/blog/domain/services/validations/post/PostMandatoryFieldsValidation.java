package com.blog.domain.services.validations.post;

import com.blog.data.models.Post;
import com.blog.domain.services.validations.CreateValidations;
import org.springframework.stereotype.Component;

@Component
public class PostMandatoryFieldsValidation implements CreateValidations<Post> {
    @Override
    public void validate(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if (post.getTitle() == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        if (post.getContent() == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
    }
}
