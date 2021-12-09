package com.group22.orderservice.controller;


import antlr.collections.List;
import com.group22.orderservice.model.Order;
import com.group22.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }


//    @GetMapping
//    public List<Order> getOrders() {
//        return orderService.getOrders();
//    }


    @PostMapping
    public void createOrder(@RequestBody Order order){
        orderService.addOrder(order);
    }


    @DeleteMapping(path = "/{orderId}")
    public void deleteOrder(@PathVariable("orderId") UUID id){
        orderService.deleteOrder(id);
    }

    @PutMapping(path = "/{orderId}")
    public void updateOrder(
            @PathVariable("orderId") UUID id,
            @RequestParam(required = false) double price,
            @RequestParam(required = false) int quantity){
        orderService.updateOrder(id, price ,quantity);
    }


    @GetMapping(path = "/{orderId}")
    public Order traceOrder(@PathVariable("orderId") UUID id){
        return orderService.traceOrder(id);
    }

}


