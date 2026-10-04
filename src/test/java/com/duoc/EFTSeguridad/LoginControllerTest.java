package com.duoc.EFTSeguridad;

import com.duoc.EFTSeguridad.usuario.MyUserDetailsService;
import com.duoc.EFTSeguridad.usuario.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    @Mock
    private JWTAuthenticationConfig jwtAuthenticationConfig;

    @Mock
    private MyUserDetailsService userDetailsService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginExitoso() {
        // Arrange
        User requestUser = new User();
        requestUser.setUsername("admin");
        requestUser.setPassword("admin123");

        UserDetails userDetailsMock = new org.springframework.security.core.userdetails.User(
                "admin", "admin123", new ArrayList<>()
        );

        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetailsMock);
        when(jwtAuthenticationConfig.getJWTToken("admin")).thenReturn("Bearer token_de_prueba");

        // Act
        String token = loginController.login(requestUser);

        // Assert
        assertEquals("Bearer token_de_prueba", token);
    }

    @Test
    void testLoginPasswordIncorrecta() {
        // Arrange
        User requestUser = new User();
        requestUser.setUsername("admin");
        requestUser.setPassword("clave_incorrecta");

        UserDetails userDetailsMock = new org.springframework.security.core.userdetails.User(
                "admin", "admin123", new ArrayList<>()
        );

        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetailsMock);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            loginController.login(requestUser);
        });
    }
}