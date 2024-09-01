package com.marcondes.FuelManager.dto.login;

public record LoginResponse(String AcessToken, Long expiresIn) {
}