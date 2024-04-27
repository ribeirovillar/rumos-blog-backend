package com.blog.domain.services.validations.post;

import com.blog.data.models.Post;
import org.springframework.stereotype.Component;

@Component
public class MandatoryFieldsValidation implements PostValidations {
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
        if (post.getAuthor() == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
    }
}
