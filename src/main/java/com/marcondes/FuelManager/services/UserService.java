package com.marcondes.FuelManager.services;

import com.marcondes.FuelManager.dto.login.LoginRequestDto;
import com.marcondes.FuelManager.dto.login.LoginResponseDto;
import com.marcondes.FuelManager.entities.Role;
import com.marcondes.FuelManager.entities.User;
import com.marcondes.FuelManager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        var user = this.findUserByEmail(loginRequestDto.getEmail());

        if (user == null || !user.isLoginCorrect(loginRequestDto, bCryptPasswordEncoder)){
            throw new BadCredentialsException("E-mail or password is incorrect");
        }

        var now = Instant.now();
        var expiresIn = 18000L;

        var scopes = "";

        if (user.getRoles() != null) {
            scopes = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));
        }

        var clains = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(clains)).getTokenValue();

        return new LoginResponseDto(jwtValue, expiresIn);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Transactional
    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
