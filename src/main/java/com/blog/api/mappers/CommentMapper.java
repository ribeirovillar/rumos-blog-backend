package com.blog.api.mappers;

import com.blog.api.dtos.CommentDTO;
import com.blog.data.models.PostComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    PostComment toComment(CommentDTO commentDTO);

    @Mapping(target = "author.email", source = "author.email")
    @Mapping(target = "author.name", expression = "java(user.getPerson().getFirstName() + \" \" + user.getPerson().getLastName())")
    CommentDTO toCommentDTO(PostComment postComment);
}
