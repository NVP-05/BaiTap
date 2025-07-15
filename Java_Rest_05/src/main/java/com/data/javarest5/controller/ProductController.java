package com.data.javarest5.controller;

import com.data.javarest5.model.entity.Product;
import com.data.javarest5.model.response.DataResponse;
import com.data.javarest5.model.response.ProductList;
import com.data.javarest5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllProducts() {
        return new ResponseEntity<>(
                new DataResponse<>(productService.getAllProducts(), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<DataResponse<Product>> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(
                new DataResponse<>(productService.createProduct(product), HttpStatus.CREATED),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(
                new DataResponse<>(productService.updateProduct(id, product), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(
                new DataResponse<>(null, HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(
                new DataResponse<>(productService.getProductById(id), HttpStatus.OK),
                HttpStatus.OK
        );
    }
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ProductList> getAllProductsXml() {
        return new ResponseEntity<>(
                new ProductList(productService.getAllProducts()),
                HttpStatus.OK
        );
    }
}
