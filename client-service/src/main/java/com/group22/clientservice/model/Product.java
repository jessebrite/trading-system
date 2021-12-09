package com.group22.clientservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Table(name = "products")
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String ticker;
    private String quantity;
//    @ManyToOne
//    @JoinColumn(name = "portfolio_id")
//    private Portfolio portfolio;

    @Column(name = "created_at", nullable = false, updatable = false)
    ZonedDateTime createdAt;
}