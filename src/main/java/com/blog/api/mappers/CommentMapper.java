package com.blog.api.mappers;

import com.blog.api.dtos.CommentDTO;
import com.blog.data.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentDTO commentDTO);

    @Mapping(target = "author.email", source = "author.user.email")
    @Mapping(target = "author.name", expression = "java(person.getFirstName() + \" \" + person.getLastName())")
    CommentDTO toCommentDTO(Comment comment);
}
