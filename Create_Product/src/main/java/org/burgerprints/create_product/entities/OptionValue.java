package org.burgerprints.create_product.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Clob;

@Entity
@Table(name = "T_OPTION_VALUE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OPTION_ID")
    @JsonIgnore
    private String optionID;

    @Lob
    @Column(name = "VALUE")
    private String value;

    @Column(name = "TYPE")
    private String type;

}
