package com.group22.clientservice.service.impl;

import com.group22.clientservice.model.Client;
import com.group22.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {
	private final ClientService clientService;

	@Override
	public UserDetails loadUserByUsername(String username)
		throws UsernameNotFoundException {
		Client client = clientService.findClientByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("Client '" + username + "' not found"));
		return UserDetailsImplementation.build(client);
	}
}

