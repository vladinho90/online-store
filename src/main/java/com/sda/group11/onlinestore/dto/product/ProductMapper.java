package com.sda.group11.onlinestore.dto.product;

import com.sda.group11.onlinestore.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductResponse toDto (Product product){
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setCategory(product.getCategory());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setStock(product.isStock());
        dto.setUnitsInStock(product.getUnitsInStock());
        dto.setPictureURL(product.getPictureURL());
        return dto;
    }

    public List<ProductResponse> toDto (List<Product> products){
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Product toEntity(ProductRequest productRequest){
        Product entity = new Product();
        entity.setCategory(productRequest.getCategory());
        entity.setTitle(productRequest.getTitle());
        entity.setPrice(productRequest.getPrice());
        entity.setStock(productRequest.isStock());
        entity.setUnitsInStock(productRequest.getUnitsInStock());
        entity.setPictureURL(productRequest.getPictureURL());
        return entity;
    }

    public List<Product> toEntity(List<ProductRequest> productRequests){
        List<Product> products = new ArrayList<>();
        for (ProductRequest productRequest : productRequests)
            products.add(toEntity(productRequest));
        return products;
    }
}
