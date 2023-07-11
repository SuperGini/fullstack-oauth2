package com.gin.repositories;

import com.gin.models.User;
import com.gin.repositories.base.HibernateRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends HibernateRepository<User>, JpaRepository<User, UUID> {


    @Query("""
            SELECT u FROM User u JOIN FETCH u.authorities
            """)
    Optional<User> findByUsername(String username);

}
