package ru.alexey.sherkhonov.security.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexey.sherkhonov.security.services.DaoUserService;

import java.security.Principal;

@RestController
@Profile("dao")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/score")
public class AllController {

    private final DaoUserService daoUserService;

    @GetMapping()
    public String homePage() {
        return "home";
    }

    @GetMapping("/get/current")
    public String scorePage(Principal principal) {
        int score = daoUserService.getScore(principal.getName());
        return "Score: " + score;
    }

    @GetMapping("/get/{id}")
    public String scorePage(@PathVariable Long id) {
        int score = daoUserService.findById(id);
        return "Score: " + score;
    }

    @GetMapping("/inc")
    public String incrementScoreByUsername(Principal principal) {
        daoUserService.incrementScoreByUsername(principal.getName());
        return "OK";
    }

    @GetMapping("/dec")
    public String decrementScoreByUsername(Principal principal) {
        daoUserService.decrementScoreByUsername(principal.getName());
        return "OK";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
