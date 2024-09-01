package com.marcondes.FuelManager.dto.user;

import com.marcondes.FuelManager.entities.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
   private String name;
   private String email;
   private Set<Role> roles = new HashSet<>();
}
