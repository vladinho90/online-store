package com.sda.group11.onlinestore.service.impl;

import com.sda.group11.onlinestore.model.enums.Category;
import com.sda.group11.onlinestore.model.Product;
import com.sda.group11.onlinestore.repository.ProductRepository;
import com.sda.group11.onlinestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    public ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product with id " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productUpdated = findById(id);
        productUpdated.setCategory(product.getCategory());
        productUpdated.setPictureURL(product.getPictureURL());
        productUpdated.setPrice(product.getPrice());
        productUpdated.setUnitsInStock(product.getUnitsInStock());
        productUpdated.setStock(product.isStock());
        productUpdated.setTitle(product.getTitle());
        return productUpdated;
    }

    @Override
    public List<Product> findAllProductsByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public void increaseStock(Long productId, int quantity) {
        Product product = findById(productId);
        if (product == null) {
            throw new RuntimeException("Product with id: " + productId + " not found");
        }
        int updateStock = product.getUnitsInStock() + quantity;
        product.setUnitsInStock(updateStock);
        update(productId,product);
        productRepository.save(product);
    }

    @Override
    public void decreaseStock(Long productId, int quantity) {
        Product product = findById(productId);
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
        update(productId,product);
        productRepository.save(product);
    }
}
