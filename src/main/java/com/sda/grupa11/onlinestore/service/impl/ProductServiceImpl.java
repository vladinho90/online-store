package com.sda.grupa11.onlinestore.service.impl;


import com.sda.grupa11.onlinestore.model.Product;
import com.sda.grupa11.onlinestore.model.enums.Category;
import com.sda.grupa11.onlinestore.repository.ProductRepository;
import com.sda.grupa11.onlinestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    public ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product with id " + id + " not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productUpdated = findProductById(id);
        productUpdated.setCategory(product.getCategory());
        productUpdated.setPictureURL(product.getPictureURL());
        productUpdated.setPrice(product.getPrice());
        productUpdated.setUnitsInStock(product.getUnitsInStock());
        productUpdated.setStock(product.isStock());
        productUpdated.setTitle(product.getTitle());
        return productUpdated;
    }

    @Override
    public List<Product> findAllProductByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }


    @Override
    public void increaseStock(Long productId, int quantity) {
        Product product = findProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product with id: " + productId + " not found");
        }
        int updateStock = product.getUnitsInStock() - quantity;
        product.setUnitsInStock(updateStock);
        updateProduct(productId,product);
    }

    @Override
    public void decreaseStock(Long productId, int quantity) {
        Product product = findProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product with id: " + productId + " not found");
        }
        int updateStock = product.getUnitsInStock() - quantity;
        if (updateStock == 0) {
            product.setStock(false);
        } else if (updateStock < 0) {
            throw new RuntimeException("Product not enough");
        }
        product.setUnitsInStock(updateStock);
        updateProduct(productId,product);
    }
}
