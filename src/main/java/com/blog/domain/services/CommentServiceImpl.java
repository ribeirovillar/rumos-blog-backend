package com.blog.domain.services;

import com.blog.configs.ApplicationContext;
import com.blog.data.models.PostComment;
import com.blog.data.repositories.CommentRepository;
import com.blog.data.repositories.PostRepository;
import com.blog.domain.exceptions.CommentNotFoundException;
import com.blog.domain.exceptions.PostNotFoundException;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.DeleteValidations;
import com.blog.domain.services.validations.UpdateValidations;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl {
    private final CommentRepository commentRepository;
    private final List<CreateValidations<PostComment>> createValidations;
    private final List<UpdateValidations<PostComment>> updateValidations;
    private final List<DeleteValidations<PostComment>> deleteValidations;
    private final ApplicationContext applicationContext;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, List<CreateValidations<PostComment>> createValidations, List<UpdateValidations<PostComment>> updateValidations, List<DeleteValidations<PostComment>> deleteValidations, ApplicationContext applicationContext, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.createValidations = createValidations;
        this.updateValidations = updateValidations;
        this.deleteValidations = deleteValidations;
        this.applicationContext = applicationContext;
        this.postRepository = postRepository;
    }

    public PostComment save(UUID postId, PostComment postComment) {
        postComment.setPostId(postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId))
                .getId());
        postComment.setAuthor(applicationContext.getUser());
        createValidations.forEach(validation -> validation.validate(postComment));
        return commentRepository.save(postComment);
    }

    public Page<PostComment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public PostComment findById(UUID id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public void delete(UUID id) {
        PostComment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        deleteValidations.forEach(validation -> validation.validate(comment));
        commentRepository.delete(comment);
    }

    public PostComment update(UUID id, PostComment comment) {
        PostComment commentOriginal = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        updateValidations.forEach(validation -> validation.validate(comment));
        BeanUtils.copyProperties(comment, commentOriginal, "id", "author", "created", "postId");
        return commentRepository.save(commentOriginal);
    }
}
