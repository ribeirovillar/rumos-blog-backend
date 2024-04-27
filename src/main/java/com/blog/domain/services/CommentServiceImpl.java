package com.blog.domain.services;

import com.blog.configs.ApplicationContext;
import com.blog.data.models.Comment;
import com.blog.data.repositories.CommentRepository;
import com.blog.domain.services.validations.comment.CommentValidations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final List<CommentValidations> validations;
    private final ApplicationContext applicationContext;

    public CommentServiceImpl(CommentRepository commentRepository, List<CommentValidations> validations, ApplicationContext applicationContext) {
        this.commentRepository = commentRepository;
        this.validations = validations;
        this.applicationContext = applicationContext;
    }

    public Comment save(Comment comment) {
        comment.setAuthor(applicationContext.getUser().getPerson());
        validations.forEach(validation -> validation.validate(comment));
        return commentRepository.save(comment);
    }
}
