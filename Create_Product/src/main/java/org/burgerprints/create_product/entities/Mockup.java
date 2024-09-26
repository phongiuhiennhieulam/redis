package org.burgerprints.create_product.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_MOCKUP")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mockup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "SRC")
    private String src;

    @Column(name = "SOURCE")
    @JsonIgnore
    private String source;

}
