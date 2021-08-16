package com.alexeysherkhonov.msauth.controllers;

import com.alexeysherkhonov.msauth.jwts.JwtProvider;
import com.alexeysherkhonov.msauth.entities.AuthRequestDto;
import com.alexeysherkhonov.msauth.entities.AuthResponseDto;
import com.alexeysherkhonov.msauth.entities.User;
import com.alexeysherkhonov.msauth.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("AuthController")
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private DefaultUserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/registration")
    public String registerNewUser(@RequestBody AuthRequestDto authRequestDto){
        User user = new User();
        user.setLogin(authRequestDto.getLogin());
        user.setPassword(authRequestDto.getPass());
        userService.saveNewUser(user);
        return "OK";
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto authRequestDto){
        User user = userService.findByLoginAndPass(authRequestDto.getLogin(),authRequestDto.getPass());
        String token = jwtProvider.generateApiToken(user.getLogin());
        return new AuthResponseDto(token);
    }
}
