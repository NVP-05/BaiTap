package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductServiceImp implements ProductService {
    public final ProductRepository productRepository;
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public boolean addProduct(Product product) {
        return productRepository.addProduct(product);
    }
    @Override
    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }
    @Override
    public boolean deleteProduct(Product product) {
        return productRepository.deleteProduct(product);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }
}
