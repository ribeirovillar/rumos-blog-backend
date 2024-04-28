package com.blog.api.mappers;

import com.blog.api.dtos.AuthRequestDTO;
import com.blog.api.dtos.UserRegistrationDTO;
import com.blog.data.models.Role;
import com.blog.data.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person.firstName", source = "firstName")
    @Mapping(target = "person.lastName", source = "lastName")
    @Mapping(target = "person.birthDate", source = "birthDate")
    @Mapping(target = "roles", ignore = true)
    User toUser(UserRegistrationDTO dto);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "firstName", source = "person.firstName")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "birthDate", source = "person.birthDate")
    @Mapping(target = "roles", expression = "java(toStringRoles(user.getRoles()))")
    UserRegistrationDTO toUserRegistrationDTO(User user);

    User toUser(AuthRequestDTO dto);


    default UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken(User user) {
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    }

    default Set<String> toStringRoles(Collection<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }
}
