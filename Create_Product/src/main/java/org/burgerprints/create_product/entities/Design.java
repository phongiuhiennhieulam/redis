package org.burgerprints.create_product.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_DESIGN")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Design implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("id")
    private String id;

    @Column(name = "BASE_DISPLAY_NAME")
    @JsonProperty("base_display_name")
    private String baseDisplayName;

    @Column(name = "PRINTABLE_TOP")
    @JsonProperty("printable_top")
    private BigDecimal printableTop;

    @Column(name = "PRINTABLE_HEIGHT")
    @JsonProperty("printable_height")
    private BigDecimal printableHeight;

    @Column(name = "PRINTABLE_WIDTH")
    @JsonProperty("printable_width")
    private BigDecimal printableWidth;



    @Column(name = "PRINTABLE_LEFT")
    @JsonProperty("printable_left")
    private BigDecimal printableLeft;

    @Column(name = "WIDTH")
    @JsonProperty("width")
    private BigDecimal width;

    @Column(name = "HEIGHT")
    @JsonProperty("height")
    private BigDecimal height;

    @Column(name = "SRC")
    @JsonProperty("src")
    private String src;

    @Column(name = "SHORT_CODE")
    @JsonProperty("short_code")
    private String shortCode;

    @Column(name = "TYPE")
    @JsonProperty("type")
    private String type;



}
