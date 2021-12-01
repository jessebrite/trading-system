package com.group22.clientservice.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private BigInteger balance;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;
}
