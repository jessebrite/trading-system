package com.group22.clientservice.payload.request;

import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
public class LoginRequest {
	@NotBlank(message = "Username is required")
	private final String username;

	@NotBlank(message = "Password is required")
	private final String lastName;
}
