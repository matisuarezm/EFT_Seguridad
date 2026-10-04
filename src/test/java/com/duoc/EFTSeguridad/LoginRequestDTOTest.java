package com.duoc.EFTSeguridad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    @Test
    void testGettersAndSetters() {
        LoginRequestDTO dto = new LoginRequestDTO();

        dto.setUsername("admin");
        dto.setPassword("12345");

        assertEquals("admin", dto.getUsername());
        assertEquals("12345", dto.getPassword());
    }
}