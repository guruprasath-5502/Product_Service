package site.guruprasath.ecom.services;

import site.guruprasath.ecom.models.Category;
import site.guruprasath.ecom.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(int productId);
    List<Product> getAllProducts();
    Product createProduct(String name, String title, String description, int price, String imageURL, Category category);
}
