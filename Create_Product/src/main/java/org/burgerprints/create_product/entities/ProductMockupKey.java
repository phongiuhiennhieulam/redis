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
public class ProductMockupKey {
    @Column(name = "PRODUCT_ID")
    private String productID;
    @Column(name = "MOCKUP_ID")
    private String mockupID;
}
