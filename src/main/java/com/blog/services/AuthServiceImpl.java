package com.blog.services;

import com.blog.dtos.AuthRequestDTO;
import com.blog.mappers.UserMapper;
import com.blog.models.Person;
import com.blog.models.Role;
import com.blog.models.User;
import com.blog.repositories.PersonRepository;
import com.blog.repositories.RoleRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.validations.RegisterValidations;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final UserMapper mapper;
    private final List<RegisterValidations> registerValidations;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(UserRepository userRepository, PersonRepository personRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtServiceImpl jwtService, UserMapper mapper, List<RegisterValidations> registerValidations, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mapper = mapper;
        this.registerValidations = registerValidations;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User register(User user) {
        registerValidations.forEach(validation -> validation.validate(user));
        createPerson(user.getPerson());
        return createUser(user);
    }

    public String login(AuthRequestDTO authRequestDTO) {
        Authentication authentication =
                authenticationManager
                        .authenticate(mapper.toUsernamePasswordAuthenticationToken(authRequestDTO));
        if (authentication.isAuthenticated()) {
            return jwtService.GenerateToken(authRequestDTO.email());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    protected void createPerson(Person person) {
        personRepository.save(person);
    }

    protected User createUser(User user) {

        user.setRoles(Set.of(retrieveRoleUser()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    protected Role retrieveRoleUser() {
        return roleRepository
                .findByName(com.blog.enums.Role.ROLE_USER.name())
                .orElseGet(() -> {
                    Role roleToCreate = new Role();
                    roleToCreate.setName(com.blog.enums.Role.ROLE_USER.name());
                    return roleRepository.save(roleToCreate);
                });
    }
}
