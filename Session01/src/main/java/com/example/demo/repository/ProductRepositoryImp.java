package com.example.demo.repository;

import com.example.demo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImp implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean addProduct(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            Product existing = entityManager.find(Product.class, product.getId());
            if (existing == null) return false;
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
        }
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        Product managed = entityManager.find(Product.class, product.getId());
        if (managed != null) {
            entityManager.remove(managed);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        Product existing = entityManager.find(Product.class, product.getId());
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }
}
