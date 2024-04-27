package com.blog.api.mappers;

import com.blog.api.dtos.AuthRequestDTO;
import com.blog.api.dtos.UserRegistrationDTO;
import com.blog.data.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person.firstName", source = "firstName")
    @Mapping(target = "person.lastName", source = "lastName")
    @Mapping(target = "person.birthDate", source = "birthDate")
    User toUser(UserRegistrationDTO dto);

    @Mapping(target = "id", source = "person.id")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "firstName", source = "person.firstName")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "birthDate", source = "person.birthDate")
    UserRegistrationDTO toUserRegistrationDTO(User user);

    User toUser(AuthRequestDTO dto);

    default UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken(User user) {
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    }


}
