package com.marcondes.FuelManager.controllers;

import com.marcondes.FuelManager.dto.role.RoleDto;
import com.marcondes.FuelManager.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;


    @GetMapping()
    public ResponseEntity<?> findRoleByName(@RequestBody RoleDto roleDto){

        RoleDto roleDB = roleService.findRoleByName(roleDto.getName());

        if (roleDB == null){
            return ResponseEntity.badRequest().body("Role inexistente");
        }
        return ResponseEntity.ok().body(roleDB);

    }


}
