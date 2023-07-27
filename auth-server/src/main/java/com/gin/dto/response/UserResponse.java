package com.gin.dto.response;

import com.gin.models.Authorities;

import java.util.Set;

public record UserResponse(

        String username,
        String email,
        Set<Authorities> authorities
) {
}
