package org.burgerprints.create_product.repositories;

import jakarta.transaction.Transactional;
import org.burgerprints.create_product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO T_Product (ID,TITLE, DESCRIPTION, TAGS, URI, SEO_TITLE, SHORT_DESC,SEO_DESC, STATUS, CATEGORY, PRODUCT_TYPE, VENDOR, USER_ID,CREATE_AT,MOCKUP_ID) " +
            "VALUES (:id,:title, :desc, :tags, :uri, :seoTitle, :shortDesc,:seoDesc, :status, :category, :productType, :vendor, :userID,SYSDATE,:mockupID)",
            nativeQuery = true)
    int insertProduct(
                      @Param("id") String id,
                      @Param("title") String title,
                      @Param("desc") String desc,
                      @Param("tags") String tags,
                      @Param("uri") String uri,
                      @Param("seoTitle") String seoTitle,
                      @Param("shortDesc") String shortDesc,
                      @Param("seoDesc") String seoDesc,
                      @Param("status") String status,
                      @Param("category") String category,
                      @Param("productType") String productType,
                      @Param("vendor") String vendor,
                      @Param("userID") String userID,
                      @Param("mockupID") String mockupID);

    @Query(value = "SELECT * FROM T_PRODUCT WHERE ID = :productId", nativeQuery = true)
    Product findProductById(@Param("productId") String productId);
}
