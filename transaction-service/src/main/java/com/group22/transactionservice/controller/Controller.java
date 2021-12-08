package com.group22.transactionservice.controller;

import com.group22.transactionservice.model.MarketData;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/marketData")
public class Controller {


    RestTemplate restTemplate;

    @GetMapping
    public List<MarketData> getMarketData(){
        List<MarketData> md = restTemplate.getForObject
                ("https://exchange2.matraining.com/md", List.class);
        System.out.println(md);
        return md;

    }



}
