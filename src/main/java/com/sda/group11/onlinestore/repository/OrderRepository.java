package com.sda.group11.onlinestore.repository;


import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.enums.Status;
import com.sda.group11.onlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);

    Optional<Order> findAllByStatus(Status status);
}
