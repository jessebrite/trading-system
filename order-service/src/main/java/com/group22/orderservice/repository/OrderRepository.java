package com.group22.orderservice.repository;

import com.group22.orderservice.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<ClientOrder, UUID> {

    ClientOrder findByProduct(String product);
    ClientOrder findByOrderId(String id);


}
