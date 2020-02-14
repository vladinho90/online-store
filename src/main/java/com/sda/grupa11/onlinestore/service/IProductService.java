package com.sda.grupa11.onlinestore.service;


import com.sda.grupa11.onlinestore.model.Product;
import com.sda.grupa11.onlinestore.model.enums.Category;

import java.util.List;

public interface IProductService {

    void saveProduct(Product product);

    List<Product> findAllProducts();

    Product findProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(Long id, Product product);

    List<Product> findAllProductByCategory(Category category);
}
