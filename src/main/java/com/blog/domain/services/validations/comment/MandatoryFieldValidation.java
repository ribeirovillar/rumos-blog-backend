package com.blog.domain.services.validations.comment;

import com.blog.data.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class MandatoryFieldValidation implements CommentValidations {
    @Override
    public void validate(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (comment.getContent() == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if (comment.getAuthor() == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (comment.getPost() == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
    }
}
