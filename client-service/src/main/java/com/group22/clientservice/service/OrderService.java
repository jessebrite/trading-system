package com.group22.clientservice.service;

import com.group22.clientservice.payload.request.OrderRequest;
import com.group22.clientservice.payload.response.OrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    public OrderResponse createOrder(OrderRequest orderRequest);
}
