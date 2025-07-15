package com.data.javarest06.service;

import com.data.javarest06.model.entity.Product;
import com.data.javarest06.model.entity.ProductCart;
import com.data.javarest06.repository.ProductCartRepository;
import com.data.javarest06.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAll(Pageable pageable, String search) {
        return productRepository.findAll(pageable);
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product update(Product productCart) {
        return productRepository.save(productCart);
    }
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
