package com.alexeysherkhonov.msauth.dtos;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String email;
    private String password;
}
