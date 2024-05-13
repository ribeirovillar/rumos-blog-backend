package com.blog.api.mappers;

import com.blog.api.dtos.CommentDTO;
import com.blog.data.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post.id", source = "postId")
    Comment toComment(CommentDTO commentDTO);

    @Mapping(target = "author.email", source = "author.email")
    @Mapping(target = "author.name", expression = "java(user.getPerson().getFirstName() + \" \" + user.getPerson().getLastName())")
    @Mapping(target = "postId", source = "post.id")
    CommentDTO toCommentDTO(Comment comment);
}
