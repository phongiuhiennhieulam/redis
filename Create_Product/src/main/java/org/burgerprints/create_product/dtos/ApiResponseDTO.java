package org.burgerprints.create_product.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponseDTO  implements Serializable {
    private int code;
    private String message;
    private Object data;
}
