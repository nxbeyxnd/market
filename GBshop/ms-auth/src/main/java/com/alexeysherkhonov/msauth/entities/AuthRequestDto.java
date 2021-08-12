package com.alexeysherkhonov.msauth.entities;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String login;
    private String pass;
}
