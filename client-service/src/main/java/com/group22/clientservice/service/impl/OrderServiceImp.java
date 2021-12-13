package com.group22.clientservice.service.impl;

import com.group22.clientservice.model.Order;
import com.group22.clientservice.payload.request.OrderRequest;
import com.group22.clientservice.payload.response.OrderResponse;
import com.group22.clientservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImp implements OrderService {

    @Value("${order-url}")
    String orderServiceUrl;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setPrice(orderRequest.getPrice());
        order.setProduct(orderRequest.getProduct());
        order.setQuantity(orderRequest.getQuantity());
        order.setSide(orderRequest.getSide());

        return this.restTemplate.postForObject(this.orderServiceUrl + "/addOrder", order, OrderResponse.class);
    }
}
