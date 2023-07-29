package com.gin.converter;

import com.gin.dto.request.UserRequest;
import com.gin.dto.request.UserRequest2;
import com.gin.dto.response.UserResponse;
import com.gin.models.Authorities;
import com.gin.models.User;
import com.gin.models.security.SecurityUser;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class UserConvertor {

    public SecurityUser convertFrom(UserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.username())
                .password(userRequest.password())
                .authorities(userRequest.authorities())
                .phoneNumbers(userRequest.phoneNumbers())
                .email(userRequest.email())
                .build();
        return new SecurityUser(user);
    }

    public SecurityUser convertFrom(UserRequest2 userRequest) {
        var auth = userRequest.getAuthorities().stream()
                .map(Authorities::new)
                .collect(Collectors.toSet());

        User user = User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .authorities(auth)
                .phoneNumbers(userRequest.getPhoneNumbers())
                .email(userRequest.getEmail())
                .build();
        return new SecurityUser(user);
    }


    public User convertFrom(SecurityUser securityUser) {
        return securityUser.getUser();
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
