package com.gin.dto.request;

import com.gin.models.Authorities;
import com.gin.models.Phone;

import java.util.Set;

public record UserRequest(

        String username,
        String email,
        String password,
        Phone phoneNumbers,
        Set<Authorities> authorities

) {
}
