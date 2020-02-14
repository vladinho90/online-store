package com.sda.grupa11.onlinestore.repository;

import com.sda.grupa11.onlinestore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
