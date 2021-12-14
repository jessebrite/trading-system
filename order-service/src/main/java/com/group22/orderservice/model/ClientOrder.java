package com.group22.orderservice.model;

import com.group22.orderservice.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Order_table")
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String product;
    private int quantity;
    private double price;
    //    @Enumerated(EnumType.STRING)
    private Side side;
    private String orderId;
}
