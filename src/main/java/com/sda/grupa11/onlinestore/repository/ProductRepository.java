package com.sda.grupa11.onlinestore.repository;

import com.sda.grupa11.onlinestore.model.Category;
import com.sda.grupa11.onlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //select * from product where category=?
    List<Product> findByCategory( Category category);
}
