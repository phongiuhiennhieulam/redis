package org.burgerprints.create_product.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "T_PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Id;


    @Column(name = "CREATE_AT")
    private String createAt;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "PRODUCT_TYPE")
    private String productType;

    @Column(name = "VENDOR")
    private String vendor;

    @Column(name = "TAGS")
    private String tags;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SHORT_DESC")
    private String shortDescription;

    @Column(name = "SEO_TITLE")
    private String seoTitle;

    @Column(name = "USER_ID")
    private String userID;

    @Column(name = "SEO_DESC")
    private String seoDescription;

    @Column(name = "URI")
    private String uri;

    @Column(name = "MOCKUP_ID")
    private String mockupID;

}
