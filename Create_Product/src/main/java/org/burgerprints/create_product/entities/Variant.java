package org.burgerprints.create_product.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_VARIANT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Variant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKU")
    private String sku;


    @Column(name = "CREATE_AT")
    @JsonIgnore
    private String createAt;


    @Column(name = "NAME")
    private String name;

    @Column(name = "COLOR_ID")
    @JsonProperty("color_id")
    private String colorID;

    @Column(name = "SIZE_ID")
    @JsonProperty("size_id")
    private String sizeID;

    @Column(name = "SHORT_CODE")
    @JsonProperty("short_code")
    private String shortCode;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "COMPARE_PRICE")
    @JsonProperty("compare_price")
    private BigDecimal comparePrice;

    @Transient
    private Mockup mockup;

    @Column(name = "MOCKUP_ID")
    @JsonProperty("mockup_id")
    private String mockupID;

    @Column(name = "PRODUCT_ID")
    @JsonIgnore
    private String productID;



}
