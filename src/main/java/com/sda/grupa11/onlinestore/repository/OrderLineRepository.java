package com.sda.grupa11.onlinestore.repository;


import com.sda.grupa11.onlinestore.model.Order;
import com.sda.grupa11.onlinestore.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    List<OrderLine> findAllByOrder(Order order);
}
