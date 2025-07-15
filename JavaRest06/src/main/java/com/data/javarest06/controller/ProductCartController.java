package com.data.javarest06.controller;

import com.data.javarest06.model.entity.ProductCart;
import com.data.javarest06.model.entity.User;
import com.data.javarest06.model.response.DataResponse;
import com.data.javarest06.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ProductCartController {

    @Autowired
    private ProductCartService productCartService;
    @GetMapping
    public ResponseEntity<DataResponse<List<ProductCart>>> getCartItems(@RequestBody User user) {
        List<ProductCart> items = productCartService.getCartItemsByUser(user);
        return new ResponseEntity<>(new DataResponse<>(items, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductCart>> addToCart(@RequestBody ProductCart productCart) {
        return new ResponseEntity<>(
                new DataResponse<>(productCartService.addToCart(productCart), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ProductCart>> updateQuantity(
            @PathVariable Long id,
            @RequestParam("quantity") Integer quantity
    ) {
        ProductCart updated = productCartService.updateQuantity(id, quantity);
        if (updated == null) {
            return new ResponseEntity<>(new DataResponse<>(null, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataResponse<>(updated, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> delete(@PathVariable Long id) {
        productCartService.removeFromCart(id);
        return new ResponseEntity<>(new DataResponse<>(null, HttpStatus.OK), HttpStatus.OK);
    }
}
