package com.gin.converter;

import com.gin.dto.request.UserRequest;
import com.gin.dto.request.UserRequest2;
import com.gin.dto.response.UserResponse;
import com.gin.models.Authorities;
import com.gin.models.User;
import com.gin.models.security.SecurityUser;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class UserConvertor {

    public User convertFrom(UserRequest2 userRequest, Set<Authorities> auth) {
        return User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .authorities(auth)
                .phoneNumbers(userRequest.getPhoneNumbers())
                .email(userRequest.getEmail())
                .build();
    }

    public UserResponse convertForm(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getAuthorities()
        );
    }

}
