package site.guruprasath.ecom.DTOs;

import lombok.Getter;
import lombok.Setter;
import site.guruprasath.ecom.models.Category;
import site.guruprasath.ecom.models.Product;

@Getter
@Setter
public class FakeStoreProductDTO { // Product type which we get from fakeStoreApi.com and which also accepts this type for their Api body
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public Product toProduct() { // method to convert fakeStoreProduct Type to our Product Type
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(image);

        Category category1 = new Category();
        category1.setTitle(category);

        product.setCategory(category1);

        return product;
    }
}
