package com.duoc.EFTSeguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Profile("default")
class WebSecurityConfig {

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                // APIs Públicas: Endpoint de login
                .requestMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
                .requestMatchers(HttpMethod.GET, Constants.LOGIN_URL).permitAll()
                
                // APIs Privadas: Todos los módulos de la veterinaria requieren autenticación JWT
                .requestMatchers("/api/clientes/**").authenticated()
                .requestMatchers("/api/mascotas/**").authenticated()
                .requestMatchers("/api/consultas/**").authenticated()
                .requestMatchers("/api/facturas/**").authenticated()
                
                // Cualquier otra solicitud debe estar autenticada
                .anyRequest().authenticated()
            )
            .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}