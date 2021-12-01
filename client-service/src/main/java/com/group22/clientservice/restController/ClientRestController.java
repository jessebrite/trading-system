package com.group22.clientservice.restController;

import com.group22.clientservice.model.Client;
import com.group22.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/clients")
@RequiredArgsConstructor
@RestController
public class ClientRestController {

    public final ClientService clientService;

    /**
     * GET: /api/v1/clients
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {

        try {
            List<Client> allClients = clientService.findAllClients();
            if (allClients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allClients, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET: /api/v1/clients/{id}
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID id) {
        try {
            Optional<Client> client = clientService.findClientById(id);
            if (client.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(client.get(), HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST: /api/v1/clients/
     *
     * @param client the client object
     * @return
     */
    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        try {
            Client clientData = clientService.save(client);
            return new ResponseEntity<>(clientData, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PUT: /api/v1/clients/{id}
     *
     * @param id     the client's id
     * @param client the client object
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable UUID id, Client client) {
        try {
            Optional<Client> clientData = clientService.findClientById(id);
            if (clientData.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            Client _client = clientData.get();
            _client.setFirstName(client.getFirstName());
            _client.setLastName(client.getLastName());
            var updatedClient = clientService.save(_client);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE: /api/v1/clients/{id}
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClientById(@PathVariable UUID id) {
        try {
            clientService.deleteClientById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE: /api/v1/clients
     *
     * @return
     */
    @DeleteMapping
    public ResponseEntity<List<Client>> deleteAllClients() {
        try {
            clientService.deleteAllClients();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
