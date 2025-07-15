package com.data.javarest06.service;

import com.data.javarest06.model.entity.Product;
import com.data.javarest06.model.entity.ProductCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getAll(Pageable pageable, String search);
    Product save(Product product);
    Product update(Product product);
    void delete(Long id);
    Product findById(Long id);
}
