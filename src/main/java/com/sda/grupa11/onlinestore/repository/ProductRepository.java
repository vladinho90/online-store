package com.sda.grupa11.onlinestore.repository;


import com.sda.grupa11.onlinestore.model.Product;
import com.sda.grupa11.onlinestore.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);
}
