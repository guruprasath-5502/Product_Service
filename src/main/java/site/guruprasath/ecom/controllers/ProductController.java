package site.guruprasath.ecom.controllers;

import org.springframework.web.bind.annotation.*;
import site.guruprasath.ecom.models.Product;
import site.guruprasath.ecom.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") int id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @PostMapping("/products")
    public void createProduct() {

    }

    @PutMapping("/products")
    public void updateProduct(int id) {

    }
}
