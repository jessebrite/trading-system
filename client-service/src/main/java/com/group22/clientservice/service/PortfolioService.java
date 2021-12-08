package com.group22.clientservice.service;

import com.group22.clientservice.model.Portfolio;
import org.springframework.http.ResponseEntity;

public interface PortfolioService {
    Portfolio createNewPortfolio();
}
