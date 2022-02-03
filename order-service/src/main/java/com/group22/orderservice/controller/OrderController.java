package com.group22.orderservice.controller;
import com.group22.orderservice.model.ClientOrder;
import com.group22.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ClientOrder addOrder(@RequestBody ClientOrder order){
        return orderService.saveOrder(order);
    }


    @GetMapping("/orders")
    public List<ClientOrder> findAllOrders(){
        return orderService.getOrders();
    }

    @GetMapping("order/{id}")
    public ClientOrder findOrderById(@PathVariable String id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/{product}")
    public ClientOrder findOrderByProduct(@PathVariable String product){
        return orderService.getOrderByProduct(product);
    }

//    @PutMapping("/update")
//    public ClientOrder updateOrder(@RequestBody ClientOrder order){
//        return orderService.updateOrder(order);
//    }




}
