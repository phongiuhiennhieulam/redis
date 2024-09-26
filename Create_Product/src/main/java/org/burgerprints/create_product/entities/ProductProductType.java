package org.burgerprints.create_product.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PRODUCT_PRODUCT_TYPE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductProductType  {
    @EmbeddedId
    private ProductProductTypeKey id;

}
