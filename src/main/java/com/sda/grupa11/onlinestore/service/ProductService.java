package com.sda.grupa11.onlinestore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.grupa11.onlinestore.dto.product.ProductMapper;
import com.sda.grupa11.onlinestore.dto.product.ProductRequest;
import com.sda.grupa11.onlinestore.dto.product.ProductResponse;
import com.sda.grupa11.onlinestore.model.Category;
import com.sda.grupa11.onlinestore.model.Product;
import com.sda.grupa11.onlinestore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private ObjectMapper jacksonObjectMapper;

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found"));
        return productMapper.toDto(product);
    }

    public List<ProductResponse> findAll(){
        List<Product> products = productRepository.findAll();
        return productMapper.toDto(products);
    }
    public List<ProductResponse> findAllByCategory(Category category){
        List<Product> products = productRepository.findByCategory(category);
        return productMapper.toDto(products);
    }

    public ProductResponse save (ProductRequest productRequest){
        Product product = productMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public ProductResponse update(Long id, ProductRequest productRequest){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found"));
        updateFields(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    private void updateFields(Product product, ProductRequest productRequest) {
        product.setCategory(productRequest.getCategory());
        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.isStock());
        product.setUnitsInStock(productRequest.getUnitsInStock());
        product.setPictureURL(productRequest.getPictureURL());
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
