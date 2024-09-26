package org.burgerprints.create_product.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PRODUCT_MOCKUP")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductMockup {
    @EmbeddedId
    private ProductMockupKey id;

}
