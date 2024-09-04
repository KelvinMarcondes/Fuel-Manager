package com.marcondes.FuelManager.dto.login;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;
    private Long expiresIn;

    public LoginResponseDto(String jwtValue, long expiresIn) {
        this.accessToken = jwtValue;
        this.expiresIn = expiresIn;
    }
}