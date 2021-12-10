package com.group22.api;

import com.google.gson.Gson;
import com.group22.dao.models.MarketData;
import com.group22.dao.repositories.MarketDataRepository;
import com.group22.dto.RawMarketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/marketdataservice")
class Controller {
    @Autowired
    private RedisTemplate template;

    @Autowired
    private ChannelTopic topic;

    @Autowired
    private Gson gson;

    private final MarketDataRepository repository;

    Controller(MarketDataRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/prices/{ticker}")
    List<MarketData> all(@PathVariable String ticker) {
        return repository.findByTicker(ticker);
    }


    @PostMapping("/subscribe")
    @ResponseBody
    public String marketDataFromExchange(@RequestBody List<RawMarketData> marketData) {
        marketData.forEach(x -> x.setExchange("EXCHANGE1"));
        template.convertAndSend(topic.getTopic(), gson.toJson(marketData));
        return "MarketData Published!";
    }


    @PostMapping("/subscribe2")
    @ResponseBody
    public String marketDataFromExchange2(@RequestBody List<RawMarketData> marketData) {
        marketData.forEach(x -> x.setExchange("EXCHANGE2"));
        template.convertAndSend(topic.getTopic(), gson.toJson(marketData));
        return "MarketData Published!";
    }


}