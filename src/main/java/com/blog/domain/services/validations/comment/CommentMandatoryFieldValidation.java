package com.blog.domain.services.validations.comment;

import com.blog.data.models.PostComment;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.UpdateValidations;
import org.springframework.stereotype.Component;

@Component
public class CommentMandatoryFieldValidation implements CreateValidations<PostComment>, UpdateValidations<PostComment> {
    @Override
    public void validate(PostComment postComment) {
        if (postComment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (postComment.getContent() == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
    }
}
