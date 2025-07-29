package ra.edu.ss15.service;

import ra.edu.ss15.model.dto.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
