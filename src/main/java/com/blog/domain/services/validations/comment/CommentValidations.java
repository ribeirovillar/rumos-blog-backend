package com.blog.domain.services.validations.comment;

import com.blog.data.models.PostComment;

public interface CommentValidations {
    void validate(PostComment postComment);
}
