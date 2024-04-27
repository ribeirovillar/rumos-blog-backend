package com.blog.domain.services.validations.comment;

import com.blog.data.models.PostComment;
import org.springframework.stereotype.Component;

@Component
public class MandatoryFieldValidation implements CommentValidations {
    @Override
    public void validate(PostComment postComment) {
        if (postComment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (postComment.getContent() == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if (postComment.getAuthor() == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

    }
}
