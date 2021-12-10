package com.group22.orderservice.controller;

import com.group22.orderservice.model.ClientOrder;
import com.group22.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ClientOrder addOrder(@RequestBody ClientOrder order){
        return orderService.saveOrder(order);
    }

    @PostMapping("{api_key}/addOrders")
    public List<ClientOrder> saveOrders(@RequestBody List<ClientOrder> orders){
        return orderService.saveOrders(orders);
    }

    @GetMapping("/orders")
    public List<ClientOrder> findAllOrders(){
        return orderService.getOrders();
    }

    @GetMapping("{api_key}/order/{id}")
    public ClientOrder findOrderById(@PathVariable UUID id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/{ticker}")
    public ClientOrder findOrderByProduct(@PathVariable String product){
        return orderService.getOrderByProduct(product);
    }

    @PutMapping("/update")
    public ClientOrder updateOrder(@RequestBody ClientOrder order){
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable UUID id){
        return orderService.deleteOrder(id);
    }

}
