package org.burgerprints.create_product.repositories;

import org.burgerprints.create_product.entities.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionValueRepository extends JpaRepository<OptionValue, String> {}
