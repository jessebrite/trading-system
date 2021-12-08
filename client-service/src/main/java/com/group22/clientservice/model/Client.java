package com.group22.clientservice.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "clients",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
        })
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //    @NotBlank
    @Size(max = 30, message = "First name should not be more than 30 characters")
    @Column(name = "first_name")
    private String firstName;

    //    @NotBlank
    @Size(max = 50, message = "Last name should not be more than 50 characters")
    @Column(name = "last_name")
    private String lastName;

    //    @NotBlank(message = "Username is required")
    @Size(max = 30, message = "Username must not be more thant 25 characters long")
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;

    //    @NotBlank(message = "Email is required")
    @Size(max = 50)
    @Email(message = "Please enter a valid email address")
    private String email;

    //    @NotBlank(message = "Password is required")
    @Size(max = 120)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    @NotNull
    private Account account;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "portfolio_ID", referencedColumnName = "id")
//    @NotNull
    private Portfolio portfolio;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
//    private List<Order> orders;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
}
