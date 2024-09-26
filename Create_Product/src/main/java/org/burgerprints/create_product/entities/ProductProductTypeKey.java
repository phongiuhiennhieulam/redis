package org.burgerprints.create_product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ProductProductTypeKey {
    @Column(name = "PRODUCT_ID")
    private String productID;

    @Column(name = "SHORT_CODE")
    private String shortCode;
}
