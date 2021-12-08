package com.group22.clientservice.service.impl;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group22.clientservice.model.Client;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@RequiredArgsConstructor
public class UserDetailsImplementation implements UserDetails {
	private static final long serialVersionUID = 1L;

	private final UUID id;
	private final String firstName;
	private final String lastName;
	private final String username;
	private final String email;

	@JsonIgnore
	private final String password;

	private final Collection<? extends GrantedAuthority> authorities;

	public static UserDetailsImplementation build(Client client) {
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) client.getRole();

		return new UserDetailsImplementation(
			client.getId(),
			client.getFirstName(),
			client.getLastName(),
			client.getUsername(),
			client.getEmail(),
			client.getPassword(),
			authorities
		);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

