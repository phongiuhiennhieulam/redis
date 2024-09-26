package org.burgerprints.create_product.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PRODUCT_OPTION_VALUE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOptionValue {
    @EmbeddedId
    private ProductOptionValueKey id;
}
