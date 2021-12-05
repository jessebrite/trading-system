package com.group22.clientservice.payload.request;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
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

	@NotBlank
	@Size(max = 50)
	@Email(message = "Please provide a valid email address")
	private final String email;

	@NotBlank(message = "Password id required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private final String password;

	@Value("${EnumType.STRING:ROLE_USER}")
	private final String role;
}
