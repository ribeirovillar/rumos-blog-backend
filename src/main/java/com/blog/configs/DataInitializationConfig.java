package com.blog.configs;

import com.blog.domain.enums.RoleEnum;
import com.blog.domain.exceptions.RoleNotFoundException;
import com.blog.data.models.Person;
import com.blog.data.models.Role;
import com.blog.data.models.User;
import com.blog.data.repositories.PersonRepository;
import com.blog.data.repositories.RoleRepository;
import com.blog.data.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Set;

@Configuration
@Order
public class DataInitializationConfig {

    @Bean
    @Order(1)
    public CommandLineRunner roleDataInitializer(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role userRole = new Role();
                userRole.setName(RoleEnum.ROLE_USER.name());
                roleRepository.save(userRole);

                Role adminRole = new Role();
                adminRole.setName(RoleEnum.ROLE_ADMIN.name());
                roleRepository.save(adminRole);
            }
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner userAdminDataInitializer(UserRepository userRepository, PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                Person person = new Person();
                person.setFirstName("Admin");
                person.setLastName("User");
                person.setBirthDate(new Date());
                personRepository.save(person);

                User user = new User();
                user.setEmail("email@fake.com");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRoles(Set.of(
                        roleRepository
                                .findByName(RoleEnum.ROLE_ADMIN.name())
                                .orElseThrow(() -> new RoleNotFoundException(RoleEnum.ROLE_ADMIN.name())),
                        roleRepository.findByName(RoleEnum.ROLE_USER.name())
                                .orElseThrow(() -> new RoleNotFoundException(RoleEnum.ROLE_USER.name()))));
                user.setPerson(person);
                userRepository.save(user);
            }

        };
    }
}
