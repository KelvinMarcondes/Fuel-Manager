package com.marcondes.FuelManager.dto.user;

import com.marcondes.FuelManager.entities.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CreateUserDto {
   private String name;
   private String email;
   private String password;
   private Set<Role> role = new HashSet<>();
}
