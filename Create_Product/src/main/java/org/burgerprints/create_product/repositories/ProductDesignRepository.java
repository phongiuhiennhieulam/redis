package org.burgerprints.create_product.repositories;

import org.burgerprints.create_product.entities.ProductDesign;
import org.burgerprints.create_product.entities.ProductDesignKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDesignRepository extends JpaRepository<ProductDesign, ProductDesignKey> {}
