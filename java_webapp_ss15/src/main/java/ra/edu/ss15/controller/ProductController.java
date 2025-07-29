package ra.edu.ss15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss15.model.dto.entity.Product;
import ra.edu.ss15.model.dto.response.APIResponse;
import ra.edu.ss15.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getAll() {
        return new ResponseEntity<>(
                new APIResponse<>(true, "Products retrieved successfully", productService.getAll(), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<APIResponse<Product>> create(@RequestBody Product product) {
        Product created = productService.create(product);
        return new ResponseEntity<>(
                new APIResponse<>(true, "Product created successfully", created, HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<APIResponse<Product>> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.update(id, product);
        return new ResponseEntity<>(
                new APIResponse<>(true, "Product updated successfully", updated, HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<APIResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(
                new APIResponse<>(true, "Product deleted successfully", null, HttpStatus.NO_CONTENT),
                HttpStatus.NO_CONTENT
        );
    }
}
