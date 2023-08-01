package com.gini.repositories;

import com.gini.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(propagation = Propagation.MANDATORY)
public interface PartRepository extends JpaRepository<Part, UUID> {
}
