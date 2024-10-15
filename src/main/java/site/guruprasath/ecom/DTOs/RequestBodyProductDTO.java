package site.guruprasath.ecom.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyProductDTO { // Request Body type which we accept from client
    private String title;
    private double price;
    private String description;
    private String imageURL;
    private String category;
}
