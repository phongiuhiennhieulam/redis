package org.burgerprints.create_product.repositories;

import jakarta.transaction.Transactional;
import org.burgerprints.create_product.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    /*Category findCategoriesByName(String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_CATEGORY (ID, NAME) " +
            "VALUES (:id, :name)", nativeQuery = true)
    void saveCategory(
                     @Param("name") String name,
                     @Param("id") String id);

     */
}
