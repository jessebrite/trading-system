package com.group22.orderservice.model;

import com.group22.orderservice.enums.Side;
import com.group22.orderservice.enums.Ticker;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_table")
@Data
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private UUID id;
    private Side side;
    private int quantity;
    private double price;

}
