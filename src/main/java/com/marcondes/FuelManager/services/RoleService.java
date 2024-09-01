package com.marcondes.FuelManager.services;

import com.marcondes.FuelManager.entities.Role;
import com.marcondes.FuelManager.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
