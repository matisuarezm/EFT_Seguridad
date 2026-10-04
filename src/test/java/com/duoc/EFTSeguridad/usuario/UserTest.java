package com.duoc.EFTSeguridad.usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User();

        user.setId(1);
        user.setUsername("juanperez");
        user.setEmail("juan@example.com");
        user.setPassword("password123");
        user.setEnabled(true);

        assertEquals(1, user.getId());
        assertEquals("juanperez", user.getUsername());
        assertEquals("juan@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertTrue(user.isEnabled());
    }

    @Test
    void testUserDetailsMethods() {
        User user = new User();

        assertNull(user.getAuthorities());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
    }
}