package com.duoc.EFTSeguridad.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setEmail("admin@duoc.cl");
        user.setPassword("secret123");
        user.setEnabled(true);
    }

    @Test
    void testLoadUserByUsernameExitoso() {
        when(userRepository.findByUsername("admin")).thenReturn(user);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername("admin");

        assertNotNull(userDetails);
        assertEquals("admin", userDetails.getUsername());
        assertEquals("secret123", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    void testLoadUserByUsernameNoEncontrado() {
        when(userRepository.findByUsername("desconocido")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            myUserDetailsService.loadUserByUsername("desconocido");
        });
    }

    @Test
    void testPasswordEncoderBean() {
        PasswordEncoder encoder = myUserDetailsService.passwordEncoder();
        assertNotNull(encoder);
        
        String encoded = encoder.encode("12345");
        assertTrue(encoder.matches("12345", encoded));
    }
}