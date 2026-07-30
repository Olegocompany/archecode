package com.free.archecode.user.service;

import com.free.archecode.user.dto.auth.request.LoginUserDtoRequest;
import com.free.archecode.user.dto.auth.request.RegisterUserDtoRequest;
import com.free.archecode.user.dto.auth.response.ContainerAuthDtoResponse;

public interface AuthService {
    public ContainerAuthDtoResponse register(RegisterUserDtoRequest data);
    public ContainerAuthDtoResponse login(LoginUserDtoRequest data);
    public ContainerAuthDtoResponse refreshToken(String refreshToken);
}
