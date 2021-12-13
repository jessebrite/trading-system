package com.group22.clientservice.repository;

import com.group22.clientservice.model.Client;
import com.group22.clientservice.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByUsername(String username);

    Optional<Client> findByPortfolioStatus(Status status);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
