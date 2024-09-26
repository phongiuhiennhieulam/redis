package org.burgerprints.create_product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ProductDesignKey {
    @Column(name = "PRODUCT_ID")
    private String productID;
    @Column(name = "DESIGN_ID")
    private String designID;
}
