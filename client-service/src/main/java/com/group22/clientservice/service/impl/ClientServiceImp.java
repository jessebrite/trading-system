package com.group22.clientservice.service.impl;

import com.group22.clientservice.model.Client;
import com.group22.clientservice.repository.ClientRepository;
import com.group22.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ResponseEntity<Client> createNewClient(Client client) {
        var clientInDb = clientRepository.findByEmail(client.getEmail());

        if (clientInDb.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Client> update(UUID id, Client client) {
        try {
            Optional<Client> clientData = clientRepository.findById(id);

            if (clientData.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            var clientInDb = clientData.get();
            clientInDb.setFirstName(client.getFirstName());
            clientInDb.setLastName(client.getLastName());
            var updatedClient = clientRepository.save(clientInDb);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<Client> findClientById(UUID id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClientById(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }
}
