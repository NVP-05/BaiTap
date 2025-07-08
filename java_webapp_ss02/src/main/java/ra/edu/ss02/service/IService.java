package ra.edu.ss02.service;

import java.util.List;
import java.util.Optional;

public interface IService <T, ID>{
    T save(T t);
    List<T> findAll();
    Optional<T> findById(ID id);
    T update(T t);
    void delete(ID id);
}
