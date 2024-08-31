package com.marcondes.FuelManager.repositories;

import com.marcondes.FuelManager.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}