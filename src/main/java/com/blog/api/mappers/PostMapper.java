package com.blog.api.mappers;

import com.blog.api.dtos.PostDTO;
import com.blog.data.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface PostMapper {
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    Post toPost(PostDTO postDTO);

    @Mapping(target = "author.email", source = "author.email")
    @Mapping(target = "author.name", expression = "java(user.getPerson().getFirstName() + \" \" + user.getPerson().getLastName())")

    PostDTO toPostDTO(Post post);
}
