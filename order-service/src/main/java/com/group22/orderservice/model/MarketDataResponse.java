package com.group22.orderservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataResponse {
    @JsonAlias("id")
    private Long id;

    @JsonAlias("timestamp")
    private Long timestamp;

    @JsonAlias("LAST_TRADED_PRICE")
    private double lastTradedPrice;

    @JsonAlias("SELL_LIMIT")
    private double sellLimit;

    @JsonAlias("BID_PRICE")
    private double bidPrice;

    @JsonAlias("ASK_PRICE")
    private double askPrice;

    @JsonAlias("BUY_LIMIT")
    private double buyLimit;

    @JsonAlias("TICKER")
    private String ticker;

    @JsonAlias("MAX_PRICE_SHIFT")
    private double maxPriceShift;

    @JsonAlias("exchange")
    private String exchange;

    @Override
    public String toString() {
        return "MarketData{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", lastTradedPrice=" + lastTradedPrice +
                ", sellLimit=" + sellLimit +
                ", bidPrice=" + bidPrice +
                ", askPrice=" + askPrice +
                ", buyLimit=" + buyLimit +
                ", ticker='" + ticker + '\'' +
                ", maxPriceShift=" + maxPriceShift +
                ", exchange=" + exchange +
                '}' + '\n';
    }

}
