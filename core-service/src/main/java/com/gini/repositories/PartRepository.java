package com.gini.repositories;

import com.gini.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(propagation = Propagation.MANDATORY)
public interface PartRepository extends JpaRepository<Part, UUID> {


    @Query("""
        SELECT p FROM Part p JOIN FETCH p.car ORDER BY p.partName ASC
    """)
    Page<Part> getPartsWithPagination(Pageable page);



}
