package com.group22.clientservice.model;

import com.group22.clientservice.model.enums.Status;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "portfolio")
@Data
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Do we need the name of the portfolio????
    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Products> products;

    public Portfolio() {
        status = Status.OPENED;
        createdAt = ZonedDateTime.now(ZoneId.of("GMT"));
        products = new ArrayList<>();
    }
}