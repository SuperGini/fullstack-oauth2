package com.gin.converter;

import com.gin.dto.request.UserRequest;
import com.gin.dto.response.UserResponse;
import com.gin.models.User;
import com.gin.models.security.SecurityUser;
import lombok.experimental.UtilityClass;

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

    public User convertFrom(SecurityUser securityUser) {
        return securityUser.getUser();
    }

    public UserResponse convertForm(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getAuthorities()
        );
    }

}
