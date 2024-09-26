package org.burgerprints.create_product.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.burgerprints.create_product.entities.*;
import org.burgerprints.create_product.requests.OptionRequest;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailProductDTO implements Serializable {
    private List<ProductType> product_types;
    private String created_at;
    private List<Variant> variants;
    private String title;
    private String vendor;
    private List<OptionRequest> options;
    private String id;
    private String seo_desc;
    private String uri;
    private Mockup mockup;
    private String seo_title;
    private String tags;
    private String product_type;
    private List<Mockup> mockups;
    private String short_desc;
    private List<Design> designs;
    private String category;
    private String status;                      
    private String mockup_id;
    private String desc;
    private String user_id;


    
}
