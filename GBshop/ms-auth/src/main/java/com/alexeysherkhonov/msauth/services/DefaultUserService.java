package com.alexeysherkhonov.msauth.services;

import com.alexeysherkhonov.msauth.entities.Role;
import com.alexeysherkhonov.msauth.entities.User;
import com.alexeysherkhonov.msauth.repositories.RoleRepository;
import com.alexeysherkhonov.msauth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveNewUser(User user){
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPass(String login, String pass){
        User user = findByLogin(login);
        if(user != null){
            if (passwordEncoder.matches(pass, user.getPassword())){
                return user;
            }
        }
        return null;
    }
}
