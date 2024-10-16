package site.guruprasath.ecom.services;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import site.guruprasath.ecom.DTOs.FakeStoreProductDTO;
import site.guruprasath.ecom.models.Category;
import site.guruprasath.ecom.models.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary // makes this to be injected as primary when there are two services which implements ProductService to avoid ambiguity
public class FakeStoreProductService implements ProductService{

    // This service is dependent on RestTemplate
    private final RestTemplate restTemplate; // Spring Boot cant create Bean in the App Context without mention @Bean or @Component or Some special annotations, So this happens in config

    // Constructor Injection
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate; // Use RestTemplate Class for calling Api's from our server
    }

    @Override
    public Product getSingleProduct(Long productId) throws IllegalArgumentException, NullPointerException {
        if(productId == 0) {
            throw new IllegalArgumentException("Invalid product Id"); // Create required Exception
        }

        ResponseEntity<FakeStoreProductDTO> response =  restTemplate.getForEntity("https://fakestoreapi.com/products/" + productId, FakeStoreProductDTO.class); // ResponseEntity contains all details sent from fakeStoreApi

        if(response.getStatusCode() != HttpStatusCode.valueOf(200)) {
            throw new HttpServerErrorException(response.getStatusCode(), "Something went wrong!");
        }

        if(response.getBody() == null) {
            throw new NullPointerException("Product not found");
        }

        return response.getBody().toProduct();
    }

    @Override
    public List<Product> getAllProducts() throws HttpServerErrorException {
        List<Product> products = new ArrayList<>();

        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        if(response.getStatusCode() != HttpStatusCode.valueOf(200)) {
            throw new HttpServerErrorException(response.getStatusCode(), "Something went wrong!");
        }

        FakeStoreProductDTO[] fakeStoreProductDTOList = response.getBody();

        if(fakeStoreProductDTOList != null) {
            for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOList) {
                products.add(fakeStoreProductDTO.toProduct());
            }
        }

        return products;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        ResponseEntity<String[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class);

        if(response.getStatusCode() != HttpStatusCode.valueOf(200)) {
            throw new HttpServerErrorException(response.getStatusCode(), "Something went wrong!");
        }

        String[] fakeStoreCategoryList = response.getBody();

        if(fakeStoreCategoryList != null) {
            for(String category : fakeStoreCategoryList) {
                categories.add(new Category(category));
            }
        }

        return categories;
    }

    @Override
    public Product createProduct(String title, String description, double price, String imageURL, String category) throws HttpServerErrorException, NullPointerException {
        FakeStoreProductDTO fakeStoreProduct = new FakeStoreProductDTO();
        fakeStoreProduct.setTitle(title);
        fakeStoreProduct.setDescription(description);
        fakeStoreProduct.setPrice(price);
        fakeStoreProduct.setImage(imageURL);
        fakeStoreProduct.setCategory(category);

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProduct, FakeStoreProductDTO.class);

        if(response.getStatusCode() != HttpStatusCode.valueOf(200)) {
            throw new HttpServerErrorException(response.getStatusCode(), "Something went wrong!");
        }

        if(response.getBody() == null) {
            throw new NullPointerException("Product not created");
        }

        return response.getBody().toProduct();
    }
}
