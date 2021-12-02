package com.group22.orderservice.service;

import com.group22.orderservice.model.Order;
import com.group22.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private Order order;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }



    public void addOrder(Order order){
//        Optional<Order> byId = orderRepository
//                .findById(order.getId());
//        if(byId.isPresent()){
//            throw new IllegalStateException("can't use this order id");
//        }
        orderRepository.save(order);
        System.out.println(order); // just checking
    }



    public void deleteOrder(UUID id) {
        orderRepository.findById(id);
        boolean b = orderRepository.existsById(id);
        if(!b) {
            throw new IllegalStateException("order with id" + id + "does not exists");
        }
        orderRepository.deleteById(id);
    }


    @Transactional
    public void updateOrder(UUID id, double price, int quantity) {
        orderRepository.
                findById(id).
                orElseThrow(()-> new IllegalStateException("order with id" + id + "does not exist"));
        if (quantity != 0 && quantity > 0 && !Objects.equals(order.getQuantity(), quantity)){
            order.setQuantity(quantity);
        }


        if (price != 0 && price > 0 && !Objects.equals(order.getPrice(),price)){
            Optional<Order> orderById = orderRepository.findOrderById(id);
            if(orderById.isPresent()){
                throw new IllegalStateException("id already exit");
            }
            order.setPrice(price);

        }

    }


    public Order traceOrder(UUID id) {
        orderRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException
                                ("order with this id is not found"));
       return order;
    }

}
