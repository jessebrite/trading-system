package com.group22.clientservice.service;

import com.group22.clientservice.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    Client save(Client client);

    Optional<Client> findClientById(UUID id);

    List<Client> findAllClients();

    void deleteClientById(UUID id);

    void deleteAllClients();

}
