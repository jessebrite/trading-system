package com.group22.clientservice.service.impl;

import com.group22.clientservice.model.Portfolio;
import com.group22.clientservice.repository.PortfolioRepository;
import com.group22.clientservice.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImp implements PortfolioService {
    @Autowired
    private final PortfolioRepository portfolioRepository;

    @Override
    public Portfolio createNewPortfolio() {
        return portfolioRepository.save(new Portfolio());
    }
}
