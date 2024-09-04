package com.marcondes.FuelManager.controllers;


import com.marcondes.FuelManager.dto.login.LoginRequestDto;
import com.marcondes.FuelManager.dto.login.LoginResponseDto;
import com.marcondes.FuelManager.dto.user.CreateUserDto;
import com.marcondes.FuelManager.entities.User;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já existente.");
        }

        User user = mapper.map(createUserDto, User.class);

        try {
            userService.createUser(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(createUserDto.getEmail());
        loginRequestDto.setPassword(createUserDto.getPassword());

        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);

    }

}