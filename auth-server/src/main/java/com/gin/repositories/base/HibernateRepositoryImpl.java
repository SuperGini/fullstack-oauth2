package com.gin.repositories.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * https://vladmihalcea.com/best-spring-data-jparepository/
 * */

public class HibernateRepositoryImpl<T> implements HibernateRepository<T>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <S extends T> S save(S entity) {
        throw new UnsupportedOperationException("method not supported");
    }

    public  <S extends T> S persist(S entity){
        entityManager.persist(entity);
        return entity;
    }
}
