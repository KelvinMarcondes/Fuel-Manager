package com.marcondes.FuelManager.dto.user;

import lombok.Data;

@Data
public class CreateUserDto {
   private String name;
   private String email;
   private String password;
}
