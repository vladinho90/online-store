package com.sda.group11.onlinestore.service;


import com.sda.group11.onlinestore.model.enums.Category;
import com.sda.group11.onlinestore.model.Product;

import java.util.List;

public interface IProductService {

    void saveProduct(Product product);

    List<Product> findAllProducts();

    Product findProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(Long id, Product product);

    List<Product> findAllProductByCategory(Category category);

    void increaseStock(Long productId, int quantity);

    void decreaseStock(Long productId, int quantity);
}
