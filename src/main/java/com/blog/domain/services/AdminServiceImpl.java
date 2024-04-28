package com.blog.domain.services;

import com.blog.data.models.Role;
import com.blog.data.models.User;
import com.blog.data.repositories.RoleRepository;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.enums.RoleEnum;
import com.blog.domain.exceptions.RoleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminServiceImpl {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User promoteUserAdmin(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Role roleAdmin = retrieveRoleAdmin();
        if(!user.getRoles().contains(roleAdmin)) {
            user.getRoles().add(retrieveRoleAdmin());
        }
        return userRepository.save(user);
    }

    public User revokeUserAdmin(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.getRoles().remove(retrieveRoleAdmin());
        return userRepository.save(user);
    }

    protected Role retrieveRoleAdmin() {
        return roleRepository.findByName(RoleEnum.ROLE_ADMIN.name())
                .orElseThrow(() -> new RoleNotFoundException(RoleEnum.ROLE_ADMIN.name()));
    }
}
