package com.blog.domain.services;

import com.blog.api.mappers.UserMapper;
import com.blog.data.models.CustomUserDetails;
import com.blog.data.models.Person;
import com.blog.data.models.Role;
import com.blog.data.models.User;
import com.blog.data.repositories.PersonRepository;
import com.blog.data.repositories.RoleRepository;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.enums.RoleEnum;
import com.blog.domain.exceptions.RoleNotFoundException;
import com.blog.domain.services.validations.CreateValidations;
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
    private final List<CreateValidations<User>> createValidations;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(UserRepository userRepository, PersonRepository personRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtServiceImpl jwtService, UserMapper mapper, List<CreateValidations<User>> createValidations, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mapper = mapper;
        this.createValidations = createValidations;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User register(User user) {
        createValidations.forEach(validation -> validation.validate(user));
        createPerson(user.getPerson());
        return createUser(user);
    }

    public String login(User user) {
        Authentication authentication =
                authenticationManager
                        .authenticate(mapper.toUsernamePasswordAuthenticationToken(user));
        if (authentication.isAuthenticated()) {
            return jwtService.GenerateToken((CustomUserDetails) authentication.getPrincipal());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    protected User createUser(User user) {
        user.setRoles(Set.of(retrieveRoleUser()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    protected Role retrieveRoleUser() {
        return roleRepository.findByName(RoleEnum.ROLE_USER.name())
                .orElseThrow(() -> new RoleNotFoundException(RoleEnum.ROLE_USER.name()));
    }

    protected void createPerson(Person person) {
        personRepository.save(person);
    }
}
