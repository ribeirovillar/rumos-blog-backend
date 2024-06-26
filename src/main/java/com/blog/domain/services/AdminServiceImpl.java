package com.blog.domain.services;

import com.blog.data.models.Role;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.enums.RoleEnum;
import com.blog.domain.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl {
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User promoteUserAdmin(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Role roleAdmin = retrieveRoleAdmin();
        if (Objects.isNull(user.getRoles())) {
            user.setRoles(Set.of(roleAdmin));
        } else if (!user.getRoles().contains(roleAdmin)) {
            user.getRoles().add(roleAdmin);
        } else {
            return user;
        }

        return userRepository.save(user);
    }

    public User revokeUserAdmin(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.getRoles().remove(retrieveRoleAdmin());
        return userRepository.save(user);
    }

    protected Role retrieveRoleAdmin() {
        return Role
                .RoleBuilder
                .aRole()
                .withId(RoleEnum.ROLE_ADMIN.getId())
                .withName(RoleEnum.ROLE_ADMIN.name())
                .build();
    }

    public Collection<Role> getUserRoles(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return Optional.ofNullable(user.getRoles()).orElse(Collections.emptySet());
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
