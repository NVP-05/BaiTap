package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
    Product getProductById(Long id);

}
