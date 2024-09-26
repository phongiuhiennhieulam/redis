package org.burgerprints.create_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CreateProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreateProductApplication.class, args);
    }

}
