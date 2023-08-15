package com.gin.repositories;

import com.gin.models.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Transactional(propagation = Propagation.MANDATORY)
public interface AuthorityRepository extends JpaRepository<Authorities, UUID> {

    @Query("""
                SELECT a FROM Authorities a WHERE a.role IN :roles
            """)
    Set<Authorities> getAuthorities(Set<String> roles);


}
