package site.guruprasath.ecom.controllers;

import org.springframework.web.bind.annotation.*;
import site.guruprasath.ecom.models.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @PostMapping("/products")
    public void createProduct() {

    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") int id) {
        return new Product();
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    public void updateProduct(int id) {

    }
}
