package org.burgerprints.create_product.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.burgerprints.create_product.entities.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProductRequest {
    private List<Mockup> mockups;
    private List<ProductType> productTypes;
    private List<Design> designs;
    private List<OptionRequest> options;
    private List<Variant> variants;
    private String mockupID;
    private String title;
    private String description;
    private String tags;
    private String uri;
    private String seoTitle;
    private String shortDescription;
    private String seoDescription;
    private String status;
    private String category;
    private String productType;
    private String vendor;
    private String userID;
}
