package com.blog.api.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Bean
    @Primary
    public CommentMapper commentMapper() {
        return new CommentMapperImpl();
    }
}
