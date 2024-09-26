package org.burgerprints.create_product.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.burgerprints.create_product.entities.OptionValue;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionRequest implements Serializable {
    @JsonProperty("display_name")
    private String displayName;
    private String name;
    private List<OptionValue> values;
}
