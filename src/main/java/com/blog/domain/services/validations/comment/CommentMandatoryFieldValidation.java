package com.blog.domain.services.validations.comment;

import com.blog.data.models.Comment;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.UpdateValidations;
import org.springframework.stereotype.Component;

@Component
public class CommentMandatoryFieldValidation implements CreateValidations<Comment>, UpdateValidations<Comment> {
    @Override
    public void validate(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (comment.getContent() == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
    }
}
