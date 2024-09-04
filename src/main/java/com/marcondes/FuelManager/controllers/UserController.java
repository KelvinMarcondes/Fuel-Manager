package com.marcondes.FuelManager.controllers;

import com.marcondes.FuelManager.dto.user.UserDto;
import com.marcondes.FuelManager.entities.User;
import com.marcondes.FuelManager.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    ModelMapper mapper = new ModelMapper();

    @GetMapping("/find-account")
    public ResponseEntity<UserDto> findByEmail(@RequestBody UserDto userDto){

        User userDB = userService.findUserByEmail(userDto.getEmail());
        if (userDB == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(mapper.map(userDB, UserDto.class));
    }




}
