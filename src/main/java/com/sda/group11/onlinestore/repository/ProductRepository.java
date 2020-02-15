package com.sda.group11.onlinestore.repository;


import com.sda.group11.onlinestore.model.enums.Category;
import com.sda.group11.onlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);
}
