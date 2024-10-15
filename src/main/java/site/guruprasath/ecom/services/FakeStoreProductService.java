package site.guruprasath.ecom.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.guruprasath.ecom.DTOs.FakeStoreProductDTO;
import site.guruprasath.ecom.models.Product;

import java.util.List;

@Service
@Primary // makes this to be injected as primary when there are two services which implements ProductService to avoid ambiguity
public class FakeStoreProductService implements ProductService{

    // This service is dependent on RestTemplate
    private RestTemplate restTemplate; // Spring Boot cant create Bean in the App Context without mention @Bean or @Component or Some special annotations, So this happens in config

    // Constructor Injection
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate; // Use RestTemplate Class for calling Api's from our server
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDTO fakeStoreResponse =  restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDTO.class);
        return fakeStoreResponse.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, String description, double price, String imageURL, String category) {
        FakeStoreProductDTO fakeStoreProduct = new FakeStoreProductDTO();
        fakeStoreProduct.setTitle(title);
        fakeStoreProduct.setDescription(description);
        fakeStoreProduct.setPrice(price);
        fakeStoreProduct.setImage(imageURL);
        fakeStoreProduct.setCategory(category);

        FakeStoreProductDTO fakeStoreResponse = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProduct, FakeStoreProductDTO.class);
        return fakeStoreResponse.toProduct();
    }
}
