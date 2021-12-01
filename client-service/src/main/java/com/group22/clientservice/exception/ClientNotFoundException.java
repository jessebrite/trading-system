package com.group22.clientservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends Exception {
    public ClientNotFoundException() {
        super("Client was not found");
    }
}
