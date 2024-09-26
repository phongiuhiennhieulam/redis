package org.burgerprints.create_product.repositories;

import org.burgerprints.create_product.entities.ProductMockup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMockupRepository extends JpaRepository<ProductMockup, String> {}
