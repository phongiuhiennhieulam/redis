package org.burgerprints.create_product.services;

import org.burgerprints.create_product.dtos.DetailProductDTO;
import org.burgerprints.create_product.entities.Product;
import org.burgerprints.create_product.requests.CreateProductRequest;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {
    String createProduct(CreateProductRequest request);
    String getProduct(String productID);
}
