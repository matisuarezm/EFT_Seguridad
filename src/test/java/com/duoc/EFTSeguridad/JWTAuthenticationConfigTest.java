package com.duoc.EFTSeguridad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTAuthenticationConfigTest {

    private final JWTAuthenticationConfig jwtAuthenticationConfig = new JWTAuthenticationConfig();

    @Test
    void testGetJWTToken() {
        String token = jwtAuthenticationConfig.getJWTToken("admin");

        assertNotNull(token);
        assertTrue(token.startsWith("Bearer "));
    }
}