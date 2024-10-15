package site.guruprasath.ecom.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration { // Create a config file for special configurations for our app

    @Bean // Use @Bean to create instance for Library classes
    public RestTemplate createRestTemplate() { // As we cant create obj by spring boot, we write the method for creating it, As we cant annotate internal Library
        return new RestTemplate();
    }
}
