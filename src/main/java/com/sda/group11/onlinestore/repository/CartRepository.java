package com.sda.group11.onlinestore.repository;

import com.sda.group11.onlinestore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

     void deleteById(Long id);
}
