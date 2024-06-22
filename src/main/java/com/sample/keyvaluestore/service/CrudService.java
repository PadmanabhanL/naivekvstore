package com.sample.keyvaluestore.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, U> {

    List<T> findAll();

    T findById(U u);

    void save(T t);

    void delete(U t);

    void cleanupEntries();

}
