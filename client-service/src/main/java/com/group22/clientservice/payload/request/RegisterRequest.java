package com.group22.clientservice.payload.request;


import com.group22.clientservice.model.Account;
import com.group22.clientservice.model.Portfolio;
import com.group22.clientservice.model.enums.Role;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
public class RegisterRequest {
    @NotBlank(message = "First name is required")
    @Size(max = 25, message = "First name should not exceed 25 characters")
    private final String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 45, message = "Last name should not exceed 35 characters")
    private final String lastName;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private final String username;

    @NotBlank(message = "Email should not be blank")
    @Size(max = 50)
    @Email(message = "Please provide a valid email address")
    private final String email;

    @NotBlank(message = "Password id required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private final String password;

    private final Account account;

    private final Portfolio portfolio;

    @Enumerated(EnumType.STRING)
    private final Role role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private final ZonedDateTime createdAt;
}
