package com.witherxee.petclinic.dao;

import java.util.List;

public interface BaseDAO<T> {

    void create(T entity);

    T findById(int id);

    List<T> findAll();

    void update(T entity);

    void delete(int id);
}