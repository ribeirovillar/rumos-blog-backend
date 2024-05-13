package com.blog.domain.services.validations.comment;

import com.blog.data.models.Comment;
import com.blog.data.models.CustomUserDetails;
import com.blog.domain.services.validations.DeleteValidations;
import com.blog.domain.services.validations.UpdateValidations;
import com.blog.infra.configs.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommentPermissionValidation implements UpdateValidations<Comment>, DeleteValidations<Comment> {
    private final ApplicationContext applicationContext;

    public CommentPermissionValidation(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void validate(Comment comment) {
        if (!applicationContext.getUser().getId().equals(comment.getAuthor().getId())) {
            if (!new CustomUserDetails(applicationContext.getUser()).isAdmin()) {
                throw new IllegalArgumentException("You must be the author or administrator to update or delete this comment.");
            }
        }
    }
}
