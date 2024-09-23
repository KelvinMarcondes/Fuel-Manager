package com.marcondes.FuelManager.controllers;


import com.marcondes.FuelManager.dto.login.LoginRequestDto;
import com.marcondes.FuelManager.dto.login.LoginResponseDto;
import com.marcondes.FuelManager.dto.user.CreateUserDto;
import com.marcondes.FuelManager.entities.User;
import com.marcondes.FuelManager.exceptions.UserAlreadyExistsException;
import com.marcondes.FuelManager.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private UserService userService;

    ModelMapper mapper = new ModelMapper();

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }


    @PostMapping("/cadastro")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto createUserDto) {

        if (userService.findUserByEmail(createUserDto.getEmail()) != null){
            throw new UserAlreadyExistsException("E-mail já existente.");
        }

        userService.createUser(mapper.map(createUserDto, User.class));
        LoginResponseDto loginResponseDto = userService.login(mapper.map(createUserDto, LoginRequestDto.class));
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);

    }

}