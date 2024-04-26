package com.blog.configs;

import com.blog.models.Role;
import com.blog.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializationConfig {

    @Bean
    public CommandLineRunner dataInitializer(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role userRole = new Role();
                userRole.setName(com.blog.enums.Role.ROLE_USER.name());
                roleRepository.save(userRole);

                Role adminRole = new Role();
                adminRole.setName(com.blog.enums.Role.ROLE_ADMIN.name());
                roleRepository.save(adminRole);
            }
        };
    }
}
