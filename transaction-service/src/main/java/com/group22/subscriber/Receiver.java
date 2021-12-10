package com.group22.subscriber;

import com.google.gson.Gson;
import com.group22.dao.models.MarketData;
import com.group22.dao.repositories.MarketDataRepository;
import com.group22.dto.RawMarketData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class Receiver implements MessageListener {
    //

    private final MarketDataRepository marketDataRepository;

    @Autowired
    public Receiver(MarketDataRepository marketDataRepository) {
        this.marketDataRepository = marketDataRepository;
    }


    Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("Consumed {}", message.toString());

        Gson gson = new Gson();
        RawMarketData[] marketData = gson.fromJson(message.toString(), (Type) RawMarketData[].class);
        Instant instant = Instant.now();
        Long milli = instant.toEpochMilli();
        List<MarketData> actualMarketData = new ArrayList<>();
        for (RawMarketData marketDatum : marketData) {
            MarketData data = new MarketData();
            data.setAskPrice(marketDatum.getAskPrice());
            data.setBidPrice(marketDatum.getBidPrice());
            data.setBuyLimit(marketDatum.getBuyLimit());
            data.setLastTradedPrice(marketDatum.getLastTradedPrice());
            data.setSellLimit(marketDatum.getSellLimit());
            data.setMaxPriceShift(marketDatum.getMaxPriceShift());
            data.setTimestamp(milli);
            data.setTicker(marketDatum.getTicker());
            data.setExchange(marketDatum.getExchange());
            actualMarketData.add(data);
        }
        actualMarketData.forEach(System.out::println);

        marketDataRepository.saveAll(actualMarketData);


    }
}
