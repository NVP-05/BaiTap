package com.data.javarest06.service;
import java.util.List;
public interface IService<T,IdType> {
    T save(T t);
    T findById(IdType id);
    T update(T t);
    void delete(IdType id);
    List<T> findAll();
}
