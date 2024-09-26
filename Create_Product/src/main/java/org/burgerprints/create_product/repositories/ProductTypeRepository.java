package org.burgerprints.create_product.repositories;

import jakarta.transaction.Transactional;
import org.burgerprints.create_product.entities.Product;
import org.burgerprints.create_product.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, String> {
    ProductType findProductTypeByShortCode(String shortCode);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_Product_TYPE (SHORT_CODE,NAME) " +
            "VALUES (:shortCode,:name)",
            nativeQuery = true)
    int insertProductType(
            @Param("shortCode") String shortCode,
            @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_PRODUCT_PRODUCT_TYPE (PRODUCT_ID,SHORT_CODE) " +
            "VALUES (:productID,:shortCode)",
            nativeQuery = true)
    void insertProductTypeProduct(
            @Param("productID") String productID,
            @Param("shortCode") String shortCode);

    @Query(value = "SELECT pt.* " +
            "FROM T_PRODUCT_TYPE pt " +
            "JOIN T_PRODUCT_PRODUCT_TYPE ppt ON pt.SHORT_CODE = ppt.SHORT_CODE " +
            "WHERE ppt.PRODUCT_ID = :productId", nativeQuery = true)
    List<ProductType> findProductTypesByProductId(@Param("productId") String productId);
}
