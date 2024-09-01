package com.marcondes.FuelManager.repositories;

import com.marcondes.FuelManager.dto.role.RoleDto;
import com.marcondes.FuelManager.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    RoleDto findByName(String roleName);
}