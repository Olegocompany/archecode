package com.free.archecode.user.dto.auth.response;

public record ContainerAuthResponse (String jwtToken, String refreshToken) {
}
