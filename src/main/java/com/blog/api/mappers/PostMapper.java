package com.blog.api.mappers;

import com.blog.api.dtos.PostDTO;
import com.blog.data.models.Person;
import com.blog.data.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    Post toPost(PostDTO postDTO);
    @Mapping(target = "author.email", source = "author.user.email")
    @Mapping(target = "author.name", expression = "java(getAuthorName(person))")
    PostDTO toPostDTO(Post post);

    default String getAuthorName(Person person){
        return person.getFirstName() + " " + person.getLastName();
    }
}
