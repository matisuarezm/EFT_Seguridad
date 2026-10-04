package com.duoc.EFTSeguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.EFTSeguridad.usuario.MyUserDetailsService;


@RestController
public class LoginController {

    @Autowired
    private JWTAuthenticationConfig jwtAuthtenticationConfig;

    private MyUserDetailsService userDetailsService;

    public LoginController(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequest) {

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        if (!userDetails.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid login");
        }

        return jwtAuthtenticationConfig.getJWTToken(loginRequest.getUsername());
    }

}