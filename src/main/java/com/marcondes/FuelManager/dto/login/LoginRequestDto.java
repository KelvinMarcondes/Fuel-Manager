package com.marcondes.FuelManager.dto.login;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginRequestDto {
    private String email;
    private String password;
}