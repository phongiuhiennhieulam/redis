package org.burgerprints.create_product.repositories;

import jakarta.transaction.Transactional;
import org.burgerprints.create_product.entities.Options;
import org.burgerprints.create_product.requests.OptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OptionRepository extends JpaRepository<Options, String> {
    Options findOptionsByDisplayName(String displayName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_OPTIONS (ID,NAME,DISPLAY_NAME) " +
            "VALUES (:id,:name, :displayName)",
            nativeQuery = true)
    void insertOption(
            @Param("id") String id,
            @Param("name") String name,
            @Param("displayName") String displayName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_OPTION_VALUE (ID,NAME,OPTION_ID,VALUE,TYPE) " +
            "VALUES (:id,:name,:optionID,:value,:type)",
            nativeQuery = true)
    void insertOptionValue(
            @Param("id") String id,
            @Param("name") String name,
            @Param("optionID") String optionID,
            @Param("value") String value,
            @Param("type") String type

            );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_PRODUCT_OPTION_VALUE (PRODUCT_ID,OPTION_VALUE_ID) " +
            "VALUES (:productID,:optionValueID)",
            nativeQuery = true)
    void insertOptionValueProduct(
            @Param("productID") String productID,
            @Param("optionValueID") String optionValueID
    );

    @Query(value = """
        SELECT pov.OPTION_VALUE_ID
        FROM T_PRODUCT_OPTION_VALUE pov
        WHERE pov.PRODUCT_ID = :productId
        """, nativeQuery = true)
    List<String> findOptionValueIdsByProductId(@Param("productId") String productId);

    @Query(value = """
        SELECT o.ID AS id,
                o.NAME AS name,
               o.DISPLAY_NAME AS displayName,
               ov.ID AS id,
               ov.NAME AS optionValueName,
               ov.VALUE AS optionValue,
               ov.TYPE AS optionValueType
        FROM T_OPTIONS o
        JOIN T_OPTION_VALUE ov ON o.ID = ov.OPTION_ID
        WHERE ov.ID IN :optionValueIds
        """, nativeQuery = true)
    List<Object[]> findOptionsByOptionValueIds(@Param("optionValueIds") List<String> optionValueIds);
}
