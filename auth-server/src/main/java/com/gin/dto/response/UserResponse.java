package com.gin.dto.response;

import com.gin.models.Authorities;

import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String email,
        Set<Authorities> authorities
) {
}
