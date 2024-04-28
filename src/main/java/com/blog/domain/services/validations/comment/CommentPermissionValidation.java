package com.blog.domain.services.validations.comment;

import com.blog.configs.ApplicationContext;
import com.blog.data.models.CustomUserDetails;
import com.blog.data.models.PostComment;
import com.blog.domain.services.validations.DeleteValidations;
import com.blog.domain.services.validations.UpdateValidations;
import org.springframework.stereotype.Component;

@Component
public class CommentPermissionValidation implements UpdateValidations<PostComment>, DeleteValidations<PostComment> {
    private final ApplicationContext applicationContext;

    public CommentPermissionValidation(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void validate(PostComment postComment) {
        if (!applicationContext.getUser().getId().equals(postComment.getAuthor().getId())) {
            if (!new CustomUserDetails(applicationContext.getUser()).isAdmin()) {
                throw new IllegalArgumentException("You must be the author or administrator to update or delete this comment.");
            }
        }
    }
}
