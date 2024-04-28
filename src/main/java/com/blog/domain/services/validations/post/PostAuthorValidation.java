package com.blog.domain.services.validations.post;

import com.blog.data.models.Post;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.UpdateValidations;
import org.springframework.stereotype.Component;

@Component
public class PostAuthorValidation implements CreateValidations<Post>, UpdateValidations<Post> {
    private final UserRepository userRepository;

    public PostAuthorValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(Post post) {
        User author = post.getAuthor();
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (!userRepository.existsById(author.getId())) {
            throw new IllegalArgumentException("Author does not exist");
        }
    }
}
