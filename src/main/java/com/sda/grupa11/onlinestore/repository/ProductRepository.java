package com.sda.grupa11.onlinestore.repository;

import com.sda.grupa11.onlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
