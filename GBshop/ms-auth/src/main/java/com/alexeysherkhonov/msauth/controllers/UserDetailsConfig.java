package com.alexeysherkhonov.msauth.controllers;

import com.alexeysherkhonov.msauth.entities.User;
import com.alexeysherkhonov.msauth.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConfig implements UserDetailsService {
    @Autowired
    private DefaultUserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
