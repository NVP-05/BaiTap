package com.data.javarest03.service;

import org.springframework.data.domain.Page;

public interface IService<T,IdType>{
    T save(T t);
    T update(T t);
    void delete(IdType id);
    Page<T> findAll(org.springframework.data.domain.Pageable pageable);
    T findById(IdType id);
}
