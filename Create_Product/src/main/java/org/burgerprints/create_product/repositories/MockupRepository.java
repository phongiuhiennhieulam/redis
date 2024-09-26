package org.burgerprints.create_product.repositories;

import jakarta.transaction.Transactional;
import org.burgerprints.create_product.entities.Mockup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MockupRepository extends JpaRepository<Mockup, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_MOCKUP (ID,SRC, SOURCE) " +
            "VALUES (:id,:src, :source)",
            nativeQuery = true)
    void insertMockup(
            @Param("id") String id,
            @Param("src") String src,
            @Param("source") String source);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_PRODUCT_MOCKUP (PRODUCT_ID,MOCKUP_ID) " +
            "VALUES (:productID,:mockupID)",
            nativeQuery = true)
    void insertMockupProduct(
            @Param("productID") String productID,
            @Param("mockupID") String mockupID);

    Mockup getMockupById(String mockupID);

    @Query(value = "SELECT m.* FROM T_MOCKUP m JOIN T_PRODUCT_MOCKUP pm ON m.id = pm.mockup_id WHERE pm.product_id = :productId", nativeQuery = true)
    List<Mockup> findMockupsByProductId(@Param("productId") String productId);
}
