package com.marcondes.FuelManager.controllers;


import com.marcondes.FuelManager.dto.login.LoginRequest;
import com.marcondes.FuelManager.dto.login.LoginResponse;
import com.marcondes.FuelManager.entities.Role;
import com.marcondes.FuelManager.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(
            JwtEncoder jwtEncoder,
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest) {

        var user = userRepository.findUserByEmail(loginRequest.email());

        if (user == null || !user.isLoginCorrect(loginRequest, bCryptPasswordEncoder)){
            throw new BadCredentialsException("E-mail or password is incorrect");
        }

        var now = Instant.now();
        var expiresIn = 18000L;

        var scopes = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));

        var clains = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(clains)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }

}