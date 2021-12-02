package com.group22.orderservice.repository;

import com.group22.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository <Order, UUID> {

    Optional<Order> findOrderById(UUID id);
}
