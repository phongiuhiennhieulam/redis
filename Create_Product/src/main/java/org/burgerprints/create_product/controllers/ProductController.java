package org.burgerprints.create_product.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.burgerprints.create_product.dtos.ApiResponseDTO;
import org.burgerprints.create_product.dtos.DetailProductDTO;
import org.burgerprints.create_product.entities.Product;
import org.burgerprints.create_product.requests.CreateProductRequest;
import org.burgerprints.create_product.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Slf4j
@EnableCaching
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        try {
            String productID = productService.createProduct(request);
            return ResponseEntity.ok(productID);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest().body("Request is invalid");
        }
    }

    @GetMapping("/{productID}")
    public ApiResponseDTO getProduct(@PathVariable String productID) {
        try {

            log.info("Start call method");
            String data = productService.getProduct(productID);
            ObjectMapper objectMapper = new ObjectMapper();
            DetailProductDTO detailProduct = objectMapper.readValue(data, DetailProductDTO.class);

            ApiResponseDTO dto = new ApiResponseDTO(200, "OK",detailProduct);
            log.info("Run and call");
            return dto;
        } catch (Exception e) {
            ApiResponseDTO dtoFail = new ApiResponseDTO(400, "Bad Request",null);
            return dtoFail;
        }



        }

    public  DetailProductDTO fromJsonString(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, DetailProductDTO.class);
    }
}
