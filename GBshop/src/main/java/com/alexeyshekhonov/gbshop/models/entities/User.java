package com.alexeyshekhonov.gbshop.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

//    @OneToMany
//    @JoinTable(name = "users_orders",
//    joinColumns = @JoinColumn(name = "user_id"),
//    inverseJoinColumns = @JoinColumn(name = "order_id"))
//    private Collection<Order> orders;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}
