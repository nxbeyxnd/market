package com.alexeysherkhonov.msauth.repositories;

import com.alexeysherkhonov.msauth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
     Role findByName(String role);
}
