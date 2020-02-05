package com.sda.grupa11.onlinestore.repository;

import com.sda.grupa11.onlinestore.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
