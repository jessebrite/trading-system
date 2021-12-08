package com.group22.clientservice.service.impl;

import com.group22.clientservice.model.Account;
import com.group22.clientservice.model.Client;
import com.group22.clientservice.repository.ClientRepository;
import com.group22.clientservice.service.AccountService;
import com.group22.clientservice.service.ClientService;
import com.group22.clientservice.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepository;
    private final AccountService accountService;
    private final PortfolioService portfolioService;
    private RestTemplate restTemplate;

    @Value("${abj.app.order-url}")
    private String ORDER_URL;

    @Override
    public ResponseEntity<?> fetchOrder() {
//        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("X-COM-PERSIST", "NO");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String getOrder = ORDER_URL + "/orders";

        return restTemplate.exchange(getOrder, HttpMethod.GET, entity, Client.class);
    }

    @Override
    public ResponseEntity<Client> createNewClient(Client client) {
        var clientInDb = clientRepository.findByEmail(client.getEmail());

        if (clientInDb.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        var newPortfolio = portfolioService.createNewPortfolio();
        var newAccount = accountService.createNewAccount(new Account(new BigInteger("10000")));

        client.setAccount(newAccount);
        client.setPortfolio(newPortfolio);
        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
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
    public Optional<Client> findClientByUsername(String username) {
        return clientRepository.findByUsername(username);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return clientRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Override
    public void deleteClientById(UUID id) {
        clientRepository.deleteById(id);
    }

}
