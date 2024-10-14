package site.guruprasath.ecom.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import site.guruprasath.ecom.models.Category;
import site.guruprasath.ecom.models.Product;

import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements ProductService{

    @Override
    public Product getSingleProduct(int productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String name, String title, String description, int price, String imageURL, Category category) {
        return null;
    }
}
