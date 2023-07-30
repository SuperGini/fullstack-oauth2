package com.gin.services;

import com.gin.converter.UserConvertor;
import com.gin.dto.request.UserRequest2;
import com.gin.dto.response.UserResponse;
import com.gin.models.User;
import com.gin.models.security.SecurityUser;
import com.gin.repositories.AuthorityRepository;
import com.gin.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers(){

        return userRepository.findAllUsers()
                .stream()
                .map(UserConvertor::convertForm)
                .toList();
    }

    public void deleteUser(UUID id){
        User proxyUser =userRepository.getReferenceById(id);
        //same shit as above
//        User user = new User();
//        user.setId(id);
        userRepository.delete(proxyUser);

    }


    public void createUser(UserRequest2 userRequest2){
        var auth = authorityRepository.getAuthorities(userRequest2.getAuthorities());
        var user = UserConvertor.convertFrom(userRequest2, auth);
        userRepository.persist(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + "was not found"));
    }



}
