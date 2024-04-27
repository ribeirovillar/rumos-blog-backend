package com.blog.domain.services;

import com.blog.configs.ApplicationContext;
import com.blog.data.models.Post;
import com.blog.data.repositories.PostRepository;
import com.blog.domain.exceptions.PostNotFoundException;
import com.blog.domain.services.validations.post.PostValidations;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl {
    private final PostRepository postRepository;
    private final List<PostValidations> postValidations;
    private final ApplicationContext applicationContext;

    public PostServiceImpl(PostRepository postRepository, List<PostValidations> postValidations, ApplicationContext applicationContext) {
        this.postRepository = postRepository;
        this.postValidations = postValidations;
        this.applicationContext = applicationContext;
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post save(Post post) {
        post.setAuthor(applicationContext.getUser().getPerson());
        postValidations.forEach(validation -> validation.validate(post));
        return postRepository.save(post);
    }

    public Post update(UUID id, Post post) {
        Post postOriginal = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        postValidations.forEach(validation -> validation.validate(post));
        BeanUtils.copyProperties(post, postOriginal, "id", "author", "created");
        return postRepository.save(postOriginal);
    }

    public Post findById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    public void delete(UUID id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id)));
    }
}
