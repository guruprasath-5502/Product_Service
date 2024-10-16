package site.guruprasath.ecom.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.guruprasath.ecom.DTOs.RequestBodyProductDTO;
import site.guruprasath.ecom.models.Category;
import site.guruprasath.ecom.models.Product;
import site.guruprasath.ecom.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    // This controller is dependent on ProductService instance, So it will be injected by spring boot
    private final ProductService productService;

    // Constructor Injection - No need to mention Autowired annotation
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long id) { // Response Entity contains not only data, but all other metadata like headers, statusCode etc..
        Product product = productService.getSingleProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody RequestBodyProductDTO request) {
        Product product = productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getPrice(),
                request.getImageURL(),
                request.getCategory());

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = productService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/products")
    public void updateProduct(int id) {

    }
}
