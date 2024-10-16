package site.guruprasath.ecom.services;

import org.springframework.web.client.HttpServerErrorException;
import site.guruprasath.ecom.models.Category;
import site.guruprasath.ecom.models.Product;

import java.util.List;

// Used interface here, As we may move to our own service with Db at later point of time
public interface ProductService {
    Product getSingleProduct(Long productId) throws IllegalArgumentException, NullPointerException;
    List<Product> getAllProducts() throws HttpServerErrorException;
    List<Category> getAllCategories();
    Product createProduct(String title, String description, double price, String imageURL, String category) throws HttpServerErrorException, NullPointerException;
}
