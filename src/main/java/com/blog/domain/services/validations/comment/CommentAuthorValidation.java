package com.blog.domain.services.validations.comment;

import com.blog.data.models.Comment;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.UpdateValidations;
import org.springframework.stereotype.Component;

@Component
public class CommentAuthorValidation implements CreateValidations<Comment>, UpdateValidations<Comment> {
    private final UserRepository userRepository;

    public CommentAuthorValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(Comment comment) {
        User author = comment.getAuthor();
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (!userRepository.existsById(author.getId())) {
            throw new IllegalArgumentException("Author does not exist");
        }
    }
}
