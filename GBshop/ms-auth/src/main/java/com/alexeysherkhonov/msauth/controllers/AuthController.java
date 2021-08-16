package com.alexeysherkhonov.msauth.controllers;

import com.alexeysherkhonov.core.interfaces.ITokenService;
import com.alexeysherkhonov.core.models.UserInfo;
import com.alexeysherkhonov.msauth.dtos.AuthRequestDto;
import com.alexeysherkhonov.msauth.dtos.AuthResponseDto;
import com.alexeysherkhonov.msauth.dtos.SignUpDto;
import com.alexeysherkhonov.msauth.entities.User;
import com.alexeysherkhonov.msauth.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private DefaultUserService userService;

    @Autowired
    private ITokenService iTokenService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody SignUpDto signUpDto) {
        if(userService.findByEmail(signUpDto.getEmail()) == null) {
            User user = new User();
            user.setPassword(signUpDto.getPassword());
            user.setEmail(signUpDto.getEmail());
            userService.saveUser(user);
        }
        else {
            return;
        }
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto authRequestDto){
        User user = userService.findByEmailAndPassword(authRequestDto.getEmail(), authRequestDto.getPassword());
        List<String> roles = new ArrayList<>();
        user.getRole().forEach(role -> roles.add(role.getName()));
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getEmail())
                .role(roles)
                .build();
        String token = iTokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }
}
