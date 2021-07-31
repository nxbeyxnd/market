package ru.alexey.sherkhonov.security.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexey.sherkhonov.security.entities.User;
import ru.alexey.sherkhonov.security.services.DaoUserService;

import java.security.Principal;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DaoController {

    private final DaoUserService daoUserService;

    @GetMapping("/dao")
    public String findByName(Principal principal) {
        Optional<User> user = daoUserService.findByUsername(principal.getName());
        return "Authorized: User:" + user;
    }
}
