package org.burgerprints.create_product.repositories;

import jakarta.transaction.Transactional;
import org.burgerprints.create_product.entities.ProductType;
import org.burgerprints.create_product.entities.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VariantRepository  extends JpaRepository<Variant, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_VARIANT (SKU, CREATE_AT, NAME, COLOR_ID, SIZE_ID, SHORT_CODE, PRICE, COMPARE_PRICE, COST, MOCKUP_ID, PRODUCT_ID) " +
            "VALUES (:sku, SYSDATE, :name, :colorID, :sizeID, :shortCode, :price, :comparePrice, :cost, :mockupID, :productID)",
            nativeQuery = true)
    void createVariant(
            @Param("sku") String sku,
            @Param("name") String name,
            @Param("colorID") String colorID,
            @Param("sizeID") String sizeID,
            @Param("shortCode") String shortCode,
            @Param("price") BigDecimal price,
            @Param("comparePrice") BigDecimal comparePrice,
            @Param("cost") BigDecimal cost,
            @Param("mockupID") String mockupID,
            @Param("productID") String productID
    );

    @Query(value = "SELECT * FROM T_VARIANT WHERE PRODUCT_ID = :productId", nativeQuery = true)
    List<Variant> findVariantsByProductId(@Param("productId") String productId);
}
