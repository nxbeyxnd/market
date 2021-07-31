package ru.alexey.sherkhonov.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexey.sherkhonov.security.entities.Role;
import ru.alexey.sherkhonov.security.entities.User;
import ru.alexey.sherkhonov.security.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Profile("dao")
@RequiredArgsConstructor
public class DaoUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public int getScore(String username) {
        return userRepository.findByUsername(username).get().getScore();
    }

    public int findById(Long id) {
        return userRepository.findById(id).get().getScore();
    }

    public void incrementScoreByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        user.setScore(user.getScore() + 1);
        userRepository.save(user);
    }

    public void decrementScoreByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        user.setScore(user.getScore() - 1);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
