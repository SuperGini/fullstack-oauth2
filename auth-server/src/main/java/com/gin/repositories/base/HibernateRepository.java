package com.gin.repositories.base;

public interface HibernateRepository<T> {

    @Deprecated(since = "12.07.2023")
    <S extends T> S save(S entity);

    <S extends T> S persist(S entity);
}
