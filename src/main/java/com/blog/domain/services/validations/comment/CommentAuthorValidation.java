package com.blog.domain.services.validations.comment;

import com.blog.data.models.PostComment;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.UpdateValidations;
import org.springframework.stereotype.Component;

@Component
public class CommentAuthorValidation implements CreateValidations<PostComment>, UpdateValidations<PostComment> {
    private final UserRepository userRepository;

    public CommentAuthorValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(PostComment postComment) {
        User author = postComment.getAuthor();
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (!userRepository.existsById(author.getId())) {
            throw new IllegalArgumentException("Author does not exist");
        }
    }
}
