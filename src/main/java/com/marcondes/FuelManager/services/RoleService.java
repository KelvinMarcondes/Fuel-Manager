package com.marcondes.FuelManager.services;

import com.marcondes.FuelManager.dto.role.RoleDto;
import com.marcondes.FuelManager.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public RoleDto findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
