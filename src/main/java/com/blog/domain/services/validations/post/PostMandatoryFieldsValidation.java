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
        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Please, inform a title");
        }
        if (post.getContent() == null || post.getContent().isEmpty()) {
            throw new IllegalArgumentException("Please, inform a content");
        }
        if (post.getCategories() == null || post.getCategories().isEmpty()) {
            throw new IllegalArgumentException("Please, select at least one category");
        }
    }
}
