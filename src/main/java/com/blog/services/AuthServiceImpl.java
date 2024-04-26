package com.blog.services;

import com.blog.dtos.AuthRequestDTO;
import com.blog.mappers.UserMapper;
import com.blog.models.Person;
import com.blog.models.User;
import com.blog.repositories.PersonRepository;
import com.blog.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final UserMapper mapper;

    public AuthServiceImpl(UserRepository userRepository, PersonRepository personRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtServiceImpl jwtService, UserMapper mapper) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mapper = mapper;
    }

    @Transactional
    public User register(User user) {
        Person saved = personRepository.save(user.getPerson());
        user.setId(saved.getId());
        user.setPerson(saved);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
}
