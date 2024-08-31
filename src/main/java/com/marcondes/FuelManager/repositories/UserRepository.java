package com.marcondes.FuelManager.repositories;

import com.marcondes.FuelManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}