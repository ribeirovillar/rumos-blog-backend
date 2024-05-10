package com.blog.domain.services;

import com.blog.api.mappers.UserMapper;
import com.blog.data.models.Person;
import com.blog.data.models.Role;
import com.blog.data.models.User;
import com.blog.data.repositories.PersonRepository;
import com.blog.data.repositories.RoleRepository;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.enums.RoleEnum;
import com.blog.domain.exceptions.RoleNotFoundException;
import com.blog.domain.services.validations.CreateValidations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtServiceImpl jwtService;
    @Mock
    private UserMapper userMapper;
    @Mock
    List<CreateValidations<User>> createValidations;
    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerShouldCreateUserWhenValidUserIsProvided() {
        User user = new User();
        Person person = new Person();
        user.setPerson(person);
        when(personRepository.save(any(Person.class))).thenReturn(person);
        when(userRepository.save(any(User.class))).thenReturn(user);
        doNothing().when(createValidations).forEach(any());
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(new Role()));
        User result = authService.register(user);

        assertThat(result).isEqualTo(user);
        verify(personRepository, times(1)).save(person);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void loginShouldReturnTokenWhenValidUserIsAuthenticated() {
        User user = new User();
        user.setEmail("test@test.com");
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtService.GenerateToken(anyString())).thenReturn("token");

        String result = authService.login(user);

        assertThat(result).isEqualTo("token");
    }

    @Test
    public void loginShouldThrowExceptionWhenUserIsNotAuthenticated() {
        User user = new User();
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        assertThrows(UsernameNotFoundException.class, () -> authService.login(user));
    }

    @Test
    public void createUserShouldSaveUserWithEncodedPasswordAndUserRole() {
        User user = new User();
        user.setPassword("password");
        Role role = new Role();
        role.setName(RoleEnum.ROLE_USER.name());
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = authService.createUser(user);

        assertThat(result.getPassword()).isEqualTo("encodedPassword");
        assertThat(result.getRoles()).contains(role);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void retrieveRoleUserShouldReturnUserRole() {
        Role role = new Role();
        role.setName(RoleEnum.ROLE_USER.name());
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(role));

        Role result = authService.retrieveRoleUser();

        assertThat(result).isEqualTo(role);
    }

    @Test
    public void retrieveRoleUserShouldThrowExceptionWhenUserRoleNotFound() {
        when(roleRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> authService.retrieveRoleUser());
    }

    @Test
    public void createPersonShouldSavePerson() {
        Person person = new Person();
        when(personRepository.save(any(Person.class))).thenReturn(person);

        authService.createPerson(person);

        verify(personRepository, times(1)).save(person);
    }
}