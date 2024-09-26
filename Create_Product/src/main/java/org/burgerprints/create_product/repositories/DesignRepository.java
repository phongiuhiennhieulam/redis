package org.burgerprints.create_product.repositories;

import jakarta.transaction.Transactional;
import org.burgerprints.create_product.entities.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DesignRepository extends JpaRepository<Design, String> {


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_DESIGN (ID,PRINTABLE_HEIGHT,PRINTABLE_WIDTH,PRINTABLE_TOP,PRINTABLE_LEFT, WIDTH,HEIGHT,SRC,SHORT_CODE,TYPE,BASE_DISPLAY_NAME) " +
            "VALUES (:id,:printHeight, :printWidth,:printTop,:printLeft,:width,:height,:src,:shortCode,:type,:baseDisplayName)",
            nativeQuery = true)
    void insertDesign(
            @Param("id") String id,
            @Param("printHeight") BigDecimal printHeight,
            @Param("printWidth") BigDecimal printWidth,
            @Param("printTop") BigDecimal printTop,
            @Param("printLeft") BigDecimal printLeft,
            @Param("width") BigDecimal width,
            @Param("height") BigDecimal height,
            @Param("src") String src,
            @Param("shortCode") String shortCode,
            @Param("type") String type,
            @Param("baseDisplayName") String baseDisplayName
    );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_PRODUCT_DESIGN (PRODUCT_ID,DESIGN_ID) " +
            "VALUES (:productID,:designID)",
            nativeQuery = true)
    void insertDesignProduct(
            @Param("productID") String productID,
            @Param("designID") String designID);

    @Query(value = "SELECT d.* FROM T_DESIGN d JOIN T_PRODUCT_DESIGN pd ON d.id = pd.design_id WHERE pd.product_id = :productId", nativeQuery = true)
    List<Design> findDesignsByProductId(@Param("productId") String productId);
}
