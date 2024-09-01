package com.marcondes.FuelManager.controllers;

import com.marcondes.FuelManager.dto.user.CreateUserDto;
import com.marcondes.FuelManager.dto.user.UserDto;
import com.marcondes.FuelManager.entities.User;
import com.marcondes.FuelManager.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    ModelMapper mapper = new ModelMapper();

    @PostMapping("/new-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto createUserDto) {

         if (userService.findUserByEmail(createUserDto.getEmail()) != null){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já existente.");
         }

         User user = mapper.map(createUserDto, User.class);
         userService.createUser(user);
        return ResponseEntity.ok().body("Usuário criado com sucesso.");
    }

    @GetMapping("/find-account")
    public ResponseEntity<UserDto> findByEmail(@RequestBody UserDto userDto){

        User userDB = userService.findUserByEmail(userDto.getEmail());
        if (userDB == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(mapper.map(userDB, UserDto.class));
    }




}
