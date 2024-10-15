package site.guruprasath.ecom.controllers;

import org.springframework.web.bind.annotation.*;
import site.guruprasath.ecom.DTOs.RequestBodyProductDTO;
import site.guruprasath.ecom.models.Product;
import site.guruprasath.ecom.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    // This controller is dependent on ProductService instance, So it will be injected by spring boot
    private ProductService productService;

    // Constructor Injection - No need to mention Autowired annotation
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody RequestBodyProductDTO request) {
        return productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getPrice(),
                request.getImageURL(),
                request.getCategory());
    }

    @PutMapping("/products")
    public void updateProduct(int id) {

    }
}
