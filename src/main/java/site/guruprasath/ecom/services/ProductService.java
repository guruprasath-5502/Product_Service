package site.guruprasath.ecom.services;

import site.guruprasath.ecom.models.Product;

import java.util.List;

// Used interface here, As we may move to our own service with Db at later point of time
public interface ProductService {
    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    Product createProduct(String title, String description, double price, String imageURL, String category);
}
