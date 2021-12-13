package com.group22.clientservice.payload.response;

import com.group22.clientservice.model.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Double quantity;
    private Double price;
    private Side side;
    private String product;
    private String orderId;
}
