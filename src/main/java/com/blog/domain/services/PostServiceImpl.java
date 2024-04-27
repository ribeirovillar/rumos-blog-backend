package com.blog.domain.services;

import com.blog.configs.ApplicationContext;
import com.blog.data.models.Post;
import com.blog.data.repositories.PostRepository;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.services.validations.post.CreatePostValidations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl {
    private final PostRepository postRepository;
    private final List<CreatePostValidations> createPostValidations;
    private final UserRepository userRepository;
    private final JwtServiceImpl jwtService;
    private final ApplicationContext applicationContext;

    public PostServiceImpl(PostRepository postRepository, List<CreatePostValidations> createPostValidations, UserRepository userRepository, JwtServiceImpl jwtService, ApplicationContext applicationContext) {
        this.postRepository = postRepository;
        this.createPostValidations = createPostValidations;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.applicationContext = applicationContext;
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post save(Post post) {
        post.setAuthor(applicationContext.getUser().getPerson());
        createPostValidations.forEach(validation -> validation.validate(post));
        return postRepository.save(post);
    }
}
