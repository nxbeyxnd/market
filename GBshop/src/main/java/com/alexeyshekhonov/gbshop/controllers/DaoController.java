package com.alexeyshekhonov.gbshop.controllers;

import com.alexeyshekhonov.gbshop.models.entities.User;
import com.alexeyshekhonov.gbshop.services.DaoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Profile("dao")
@RequiredArgsConstructor
public class DaoController {
    private final DaoUserService daoUserService;

    @GetMapping("/dao")
    public String findByName(Principal principal) {
        Optional<User> user = daoUserService.findByUsername(principal.getName());
        return "Authorized: User:" + user;
    }
}
