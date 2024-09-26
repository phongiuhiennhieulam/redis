package org.burgerprints.create_product.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "T_PRODUCT_TYPE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHORT_CODE")
    @JsonProperty("short_code")
    private String shortCode;

    @Column(name = "NAME")
    private String name;



}
