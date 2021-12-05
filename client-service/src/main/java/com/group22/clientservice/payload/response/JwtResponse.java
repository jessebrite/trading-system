package com.group22.clientservice.payload.response;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class JwtResponse {
	private final String token;
	private final String type = "Bearer";
	private final UUID id;
	private final String firstname;
	private final String lastname;
	private final String username;
	private final String email;
	private final String roles;
}

