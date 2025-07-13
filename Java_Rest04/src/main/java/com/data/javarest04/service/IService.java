package com.data.javarest04.service;

import com.data.javarest04.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T, IdType> {
    T add(T t);
    T update(T t);
    void delete(IdType id);
    T findById(IdType id);
    Page<T> getAll(Pageable pageable);
}
