package com.sda.grupa11.onlinestore.repository;

import com.sda.grupa11.onlinestore.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
