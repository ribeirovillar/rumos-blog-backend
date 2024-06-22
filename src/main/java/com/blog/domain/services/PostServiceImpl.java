package com.blog.domain.services;

import com.blog.infra.configs.ApplicationContext;
import com.blog.data.models.Post;
import com.blog.data.repositories.PostRepository;
import com.blog.domain.exceptions.PostNotFoundException;
import com.blog.domain.services.validations.CreateValidations;
import com.blog.domain.services.validations.UpdateValidations;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl {
    private final PostRepository postRepository;
    private final List<CreateValidations<Post>> createValidations;
    private final List<UpdateValidations<Post>> updateValidations;
    private final ApplicationContext applicationContext;

    public PostServiceImpl(PostRepository postRepository, List<CreateValidations<Post>> createValidations, List<UpdateValidations<Post>> updateValidations, ApplicationContext applicationContext) {
        this.postRepository = postRepository;
        this.createValidations = createValidations;
        this.updateValidations = updateValidations;
        this.applicationContext = applicationContext;
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post save(Post post) {
        post.setAuthor(applicationContext.getUser());
        createValidations.forEach(validation -> validation.validate(post));
        return postRepository.save(post);
    }

    public Post update(UUID id, Post post) {
        Post postOriginal = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        BeanUtils.copyProperties(post, postOriginal, "id", "author", "created", "postComments");
        createValidations.forEach(validation -> validation.validate(postOriginal));
        return postRepository.save(postOriginal);
    }

    public Post findById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional
    public void delete(UUID id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id)));
    }
}
