package ru.alexey.sherkhonov.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexey.sherkhonov.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
