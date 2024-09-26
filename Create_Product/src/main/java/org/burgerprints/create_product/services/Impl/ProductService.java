package org.burgerprints.create_product.services.Impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import jakarta.transaction.Transactional;
import org.burgerprints.create_product.dtos.DetailProductDTO;
import org.burgerprints.create_product.entities.*;
import org.burgerprints.create_product.repositories.*;
import org.burgerprints.create_product.requests.CreateProductRequest;
import org.burgerprints.create_product.requests.OptionRequest;
import org.burgerprints.create_product.services.IGenerateIdService;
import org.burgerprints.create_product.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {






    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private IGenerateIdService generateIdService;
    @Autowired
    private MockupRepository mockupRepository;
    @Autowired
    private DesignRepository designRepository;
    @Autowired
    private VariantRepository variantRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Override
    @Transactional
    public String createProduct(CreateProductRequest request) {

        //Tao product truoc tien
        String productID = generateIdService.generateId();
        try {
            productRepository.insertProduct(
                    productID,
                    request.getTitle(),
                    request.getDescription(),
                    request.getTags(),
                    request.getUri(),
                    request.getSeoTitle(),
                    request.getShortDescription(),
                    request.getSeoDescription(),
                    request.getStatus(),
                    request.getCategory(),
                    request.getProductType(),
                    request.getVendor(),
                    request.getUserID(),
                    request.getMockupID()
            );

            //Xu li them mockup
            for(Mockup mockup:request.getMockups()){
                mockupRepository.insertMockup(mockup.getId(),mockup.getSrc(),mockup.getSource());
                mockupRepository.insertMockupProduct(productID,mockup.getId());
            }

            //Xu li them product type
            for(ProductType productType:request.getProductTypes()){
                ProductType productTypeEntity = productTypeRepository.findProductTypeByShortCode(productType.getShortCode());
                if(productTypeEntity == null){
                    productTypeRepository.insertProductType(productType.getShortCode(),productType.getName());
                }
                productTypeRepository.insertProductTypeProduct(productID,productType.getShortCode());
            }

            //Xu li design
            for(Design design:request.getDesigns()){
                designRepository.insertDesign(design.getId(),design.getPrintableHeight(),design.getPrintableWidth(),design.getPrintableTop(),design.getPrintableLeft(),design.getWidth(),design.getHeight(),design.getSrc(),design.getShortCode(),design.getType(),design.getBaseDisplayName());
                designRepository.insertDesignProduct(productID,design.getId());
            }


            //Xu li variant
            for (Variant variant:request.getVariants()){
                variantRepository.createVariant(variant.getSku(),variant.getName(),variant.getColorID(),variant.getSizeID(),variant.getShortCode(),variant.getPrice(),variant.getComparePrice(),variant.getCost(),variant.getMockupID(),productID);
            }

            //xu li option

            for(OptionRequest optionRequest:request.getOptions()){
                Options option = optionRepository.findOptionsByDisplayName(optionRequest.getDisplayName());
                String optionID = "";
                if(option == null){
                     optionID = generateIdService.generateId();
                     optionRepository.insertOption(optionID,optionRequest.getName(),optionRequest.getDisplayName());
                }else{
                    optionID = option.getId();
                }

                for (OptionValue optionValue:optionRequest.getValues()){
                    optionRepository.insertOptionValue(optionValue.getId(),optionValue.getName(),optionID,optionValue.getValue(),optionValue.getType());
                    optionRepository.insertOptionValueProduct(productID,optionValue.getId());
                }
            }






        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    @Cacheable(value = "product",key = "#productID")
    public String getProduct(String productID) {
        DetailProductDTO dtoReturn = new DetailProductDTO();
        Product product = productRepository.findProductById(productID);
        try{
            //Lay ra list product type
            dtoReturn.setProduct_types(productTypeRepository.findProductTypesByProductId(productID));
            dtoReturn.setCreated_at(product.getCreateAt().toString());

            //Xu li lay ra list variants
            List<Variant> variantList = variantRepository.findVariantsByProductId(productID);
            for (Variant variant:variantList){
                Mockup mockup = mockupRepository.getMockupById(variant.getMockupID());
                variant.setMockup(mockup);
            }

            dtoReturn.setTitle(product.getTitle());
            dtoReturn.setVendor(product.getVendor());

            //xu li lay ra cac option


             // Lấy ra các option value có productID trùng productID truyền vào
            List<String> optionValueIds = optionRepository.findOptionValueIdsByProductId(productID);

            if (optionValueIds.isEmpty()) {
                dtoReturn.setOptions(new ArrayList<>());
            }

             //Từ list id của option value lấy ra các option tương ứng của nó
            List<Object[]> rawOptions = optionRepository.findOptionsByOptionValueIds(optionValueIds);

            // Chuyen ve dang json mong muon
            Map<String, List<OptionValue>> groupedOptions = rawOptions.stream()
                    .collect(Collectors.groupingBy(
                            record -> (String) record[0],
                            Collectors.mapping(record -> new OptionValue(
                                    (String) record[3], // id
                                    (String) record[4],// optionValueName
                                    (String) record[0],//option ID
                                    (String) record[5], // optionValue
                                    (String) record[6]  // optionValueType
                            ), Collectors.toList())
                    ));
            //Hoan thien lai json dung voi json mong muon

            List<OptionRequest> optionResponses = new ArrayList<>();
            for (Map.Entry<String, List<OptionValue>> entry : groupedOptions.entrySet()) {
                String optionID = entry.getKey();
                List<OptionValue> optionValues = entry.getValue();

                // Find the display name for this option
                String name = rawOptions.stream()
                        .filter(record -> record[0].equals(optionID))
                        .map(record -> (String) record[1])
                        .findFirst()
                        .orElse("");
                String displayName = rawOptions.stream()
                        .filter(record -> record[0].equals(optionID))
                        .map(record -> (String) record[2])
                        .findFirst()
                        .orElse("");

                OptionRequest optionResponse = new OptionRequest(name, displayName, optionValues);
                optionResponses.add(optionResponse);
            }

            dtoReturn.setOptions(optionResponses);

            dtoReturn.setId(product.getId());
            dtoReturn.setSeo_desc(product.getSeoDescription());
            dtoReturn.setUri(product.getUri());
            dtoReturn.setMockup(mockupRepository.getMockupById(product.getMockupID()));
            dtoReturn.setSeo_title(product.getSeoTitle());
            dtoReturn.setTags(product.getTags());
            dtoReturn.setProduct_type(product.getProductType());
            dtoReturn.setUser_id(product.getUserID());

            //lay ra list mockup
            dtoReturn.setMockups(mockupRepository.findMockupsByProductId(productID));
            dtoReturn.setShort_desc(product.getShortDescription());

            //lay ra list design
            dtoReturn.setDesigns(designRepository.findDesignsByProductId(productID));


            dtoReturn.setCategory(product.getCategory());
            dtoReturn.setStatus(product.getStatus());
            dtoReturn.setMockup_id(product.getMockupID());
            dtoReturn.setDesc(product.getDescription());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(dtoReturn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String clobToString(Clob clob) throws SQLException, IOException {
        if (clob == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try (Reader reader = clob.getCharacterStream()) {
            char[] buffer = new char[1024];
            int numCharsRead;
            while ((numCharsRead = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, numCharsRead);
            }
        }
        return sb.toString();
    }
}

