package com.alexeysherkhonov.core.interfaces;

import com.alexeysherkhonov.core.models.UserInfo;

public interface ITokenService {
    String generateToken(UserInfo user);
    UserInfo parseToken(String token);
}
