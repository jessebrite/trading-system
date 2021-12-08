package com.group22.clientservice.restController;

import com.group22.clientservice.model.Portfolio;
import com.group22.clientservice.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfolioRestController {
    @Autowired
    private final PortfolioService portfolioService;

//    @PostMapping()
//    public ResponseEntity<Portfolio> newPortfolio() {
//        return portfolioService.createNewPortfolio();
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable UUID id) {
        return null;
    }
}
