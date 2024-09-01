package com.marcondes.FuelManager.controllers;

import com.marcondes.FuelManager.dto.role.RoleDto;
import com.marcondes.FuelManager.entities.Role;
import com.marcondes.FuelManager.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping()
    public ResponseEntity<RoleDto> findRoleByName(@RequestBody RoleDto roleDto){

        Role roleDB = roleService.findRoleByName(roleDto.getName());
        if (roleDB == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(modelMapper.map(roleDB, RoleDto.class));
    }


    @GetMapping("/all-roles")
    public ResponseEntity<List<RoleDto>> findAllRoles(){

        List<Role> rolesDB = roleService.findAllRoles();
        if (rolesDB.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        List<RoleDto> rolesDto = rolesDB.stream()
                .map(role -> modelMapper.map(role, RoleDto.class)).toList();

        return ResponseEntity.ok().body(rolesDto);
    }


}
