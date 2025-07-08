package com.example.demo.repository;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
    Product getProductById(Long id);
}
