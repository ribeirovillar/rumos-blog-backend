package com.blog.domain.services.validations.comment;

import com.blog.data.models.Comment;
import com.blog.data.models.Person;
import com.blog.data.repositories.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidation implements CommentValidations {
    private final PersonRepository personRepository;

    public AuthorValidation(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void validate(Comment comment) {
        Person author = comment.getAuthor();
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (!personRepository.existsById(author.getId())) {
            throw new IllegalArgumentException("Author does not exist");
        }
    }
}
