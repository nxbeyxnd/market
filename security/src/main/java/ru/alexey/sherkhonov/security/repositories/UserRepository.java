package ru.alexey.sherkhonov.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexey.sherkhonov.security.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
