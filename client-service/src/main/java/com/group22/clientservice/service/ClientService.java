package com.group22.clientservice.service;

import com.group22.clientservice.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
	ResponseEntity<?> fetchOrder();

	ResponseEntity<Client> createNewClient(Client client);

	ResponseEntity<Client> update(UUID id, Client client);

	Optional<Client> findClientById(UUID id);

	Optional<Client> findClientByUsername(String username);

	List<Client> findAllClients();

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	void deleteClientById(UUID id);
}
