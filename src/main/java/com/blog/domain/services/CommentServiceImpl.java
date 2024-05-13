package com.blog.domain.services;

import com.blog.data.models.Comment;
import com.blog.data.repositories.CommentRepository;
import com.blog.data.repositories.PostRepository;
import com.blog.domain.exceptions.CommentNotFoundException;
import com.blog.domain.exceptions.PostNotFoundException;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.DeleteValidations;
import com.blog.domain.services.validations.UpdateValidations;
import com.blog.infra.configs.ApplicationContext;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final List<CreateValidations<Comment>> createValidations;
    private final List<UpdateValidations<Comment>> updateValidations;
    private final List<DeleteValidations<Comment>> deleteValidations;
    private final ApplicationContext applicationContext;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, List<CreateValidations<Comment>> createValidations, List<UpdateValidations<Comment>> updateValidations, List<DeleteValidations<Comment>> deleteValidations, ApplicationContext applicationContext, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.createValidations = createValidations;
        this.updateValidations = updateValidations;
        this.deleteValidations = deleteValidations;
        this.applicationContext = applicationContext;
        this.postRepository = postRepository;
    }

    public Comment save(UUID postId, Comment comment) {
        comment.setPost(postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId)));
        comment.setAuthor(applicationContext.getUser());
        createValidations.forEach(validation -> validation.validate(comment));
        return commentRepository.save(comment);
    }

    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Comment findById(UUID id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public void delete(UUID id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        deleteValidations.forEach(validation -> validation.validate(comment));
        commentRepository.delete(comment);
    }

    public Comment update(UUID id, Comment comment) {
        Comment commentOriginal = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        updateValidations.forEach(validation -> validation.validate(comment));
        BeanUtils.copyProperties(comment, commentOriginal, "id", "author", "created", "postId");
        return commentRepository.save(commentOriginal);
    }
}
