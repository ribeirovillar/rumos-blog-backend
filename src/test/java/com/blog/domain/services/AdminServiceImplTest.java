package com.blog.domain.services;

import com.blog.data.models.Role;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class AdminServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    private UUID userId;
    private User user;
    private Role roleAdmin;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userId = UUID.randomUUID();
        user = new User();
        user.setId(userId);
        user.setRoles(new HashSet<>());

        roleAdmin = new Role();
        roleAdmin.setId(RoleEnum.ROLE_ADMIN.getId());
        roleAdmin.setName(RoleEnum.ROLE_ADMIN.name());
    }

    @Test
    public void promoteUserAdminShouldAddAdminRoleWhenUserDoesNotHaveIt() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        adminService.promoteUserAdmin(userId);

        assertThat(user.getRoles()).contains(roleAdmin);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void promoteUserAdminShouldNotAddAdminRoleWhenUserAlreadyHasIt() {
        user.getRoles().add(roleAdmin);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        adminService.promoteUserAdmin(userId);

        assertThat(user.getRoles()).containsOnlyOnce(roleAdmin);
        verify(userRepository, times(0)).save(user);
    }

    @Test
    public void revokeUserAdminShouldRemoveAdminRoleWhenUserHasIt() {
        user.getRoles().add(roleAdmin);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        adminService.revokeUserAdmin(userId);

        assertThat(user.getRoles()).doesNotContain(roleAdmin);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void revokeUserAdminShouldNotRemoveAdminRoleWhenUserDoesNotHaveIt() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        adminService.revokeUserAdmin(userId);

        assertThat(user.getRoles()).doesNotContain(roleAdmin);
        verify(userRepository, times(0)).save(user);
    }

    @Test
    public void isUserAdminReturnsTrueWhenUserHasAdminRole() {
        user.getRoles().add(roleAdmin);
        boolean result = adminService.isUserAdmin(user);
        assertThat(result).isTrue();
    }

    @Test
    public void isUserAdminReturnsFalseWhenUserDoesNotHaveAdminRole() {
        boolean result = adminService.isUserAdmin(user);
        assertThat(result).isFalse();
    }

    @Test
    public void isUserAdminReturnsFalseWhenUserHasOtherRoles() {
        Role roleUser = new Role();
        roleUser.setId(RoleEnum.ROLE_USER.getId());
        roleUser.setName(RoleEnum.ROLE_USER.name());
        user.getRoles().add(roleUser);
        boolean result = adminService.isUserAdmin(user);
        assertThat(result).isFalse();
    }
}