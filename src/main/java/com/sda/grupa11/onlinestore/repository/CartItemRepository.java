package com.sda.grupa11.onlinestore.repository;


import com.sda.grupa11.onlinestore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
