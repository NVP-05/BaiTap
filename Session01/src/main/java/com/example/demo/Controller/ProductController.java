package com.example.demo.Controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "listProduct";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        boolean exists = productService.getAllProducts().stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()));
        if (exists) {
            return "redirect:/products";
        }
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) return "redirect:/products";
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        boolean exists = productService.getAllProducts().stream()
                .anyMatch(p -> !p.getId().equals(product.getId()) &&
                        p.getName().equalsIgnoreCase(product.getName()));
        if (exists) {
            return "redirect:/products";
        }
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(product);
        }
        return "redirect:/products";
    }
}
