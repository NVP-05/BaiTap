package com.data.javarest06.service;

import com.data.javarest06.model.entity.ProductCart;
import com.data.javarest06.model.entity.User;
import com.data.javarest06.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartService {

    @Autowired
    private ProductCartRepository productCartRepository;

    public List<ProductCart> getCartItemsByUser(User user) {
        return productCartRepository.findByUser(user);
    }

    public ProductCart addToCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    public ProductCart updateQuantity(Long id, Integer quantity) {
        ProductCart cart = productCartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.setQuantity(quantity);
            return productCartRepository.save(cart);
        }
        return null;
    }

    public void removeFromCart(Long id) {
        productCartRepository.deleteById(id);
    }
}
