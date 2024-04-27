package com.blog.domain.services.validations.comment;

import com.blog.data.models.PostComment;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidation implements CommentValidations {
    private final UserRepository userRepository;

    public AuthorValidation(UserRepository userRepository) {
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
