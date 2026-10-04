package com.duoc.EFTSeguridad;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JWTAuthorizationFilterTest {

    @InjectMocks
    private JWTAuthorizationFilter filter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private final JWTAuthenticationConfig jwtConfig = new JWTAuthenticationConfig();

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterInternalSinHeader() throws ServletException, IOException {
        when(request.getHeader(Constants.HEADER_AUTHORIZACION_KEY)).thenReturn(null);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternalHeaderInvalido() throws ServletException, IOException {
        when(request.getHeader(Constants.HEADER_AUTHORIZACION_KEY)).thenReturn("Basic 123456");

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternalTokenValido() throws ServletException, IOException {
        String token = jwtConfig.getJWTToken("admin");
        when(request.getHeader(Constants.HEADER_AUTHORIZACION_KEY)).thenReturn(token);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals("admin", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    
}