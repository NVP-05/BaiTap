package com.data.javarest06.controller;

import com.data.javarest06.model.entity.Product;
import com.data.javarest06.model.response.DataResponse;
import com.data.javarest06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<DataResponse<Page<Product>>> getAllCartItems(
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable,

            @RequestParam(required = false) String search
    ) {
        Page<Product> carts = productService.getAll(pageable, search);
        return new ResponseEntity<>(new DataResponse<>(carts, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Product>> save(@RequestBody Product product) {
        return new ResponseEntity<>(new DataResponse<>(productService.save(product), HttpStatus.OK), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> update(@PathVariable("id") Long id, @RequestBody Product productCart) {
        return new ResponseEntity<>(new DataResponse<>(productService.update(productCart), HttpStatus.OK), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return new ResponseEntity<>(new DataResponse<>(null, HttpStatus.OK), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new DataResponse<>(productService.findById(id), HttpStatus.OK), HttpStatus.OK);
    }
}
