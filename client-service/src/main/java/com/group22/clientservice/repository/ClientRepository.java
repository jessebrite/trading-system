package com.group22.clientservice.repository;

import com.group22.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client save(Client client);

    Optional<Client> findById(UUID id);

    List<Client> findByFirstNameContaining(String name);

    List<Client> findAll();

    void deleteById(UUID id);

    void deleteAll();
}
