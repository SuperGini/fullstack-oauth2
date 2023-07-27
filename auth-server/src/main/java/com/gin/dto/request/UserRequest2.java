package com.gin.dto.request;

import com.gin.models.Authorities;
import com.gin.models.Phone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest2 {

    private String username;
    private String email;
    private String password;
    private Phone phoneNumbers;
    private Set<String> authorities = new HashSet<>();

}
