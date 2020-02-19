package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.enums.Category;
import com.sda.group11.onlinestore.model.Product;
import java.util.List;

public interface IProductService {

    void save(Product product);

    //ProductResponse saveProduct(ProductRequest request);

    List<Product> findAll();

    Product findById(Long id);

    void delete(Long id);

    Product update(Long id, Product product);

    List<Product> findAllProductsByCategory(Category category);

    void increaseStock(Long productId, int quantity);

    void decreaseStock(Long productId, int quantity);
}
