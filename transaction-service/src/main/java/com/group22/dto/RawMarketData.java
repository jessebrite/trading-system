package com.group22.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMarketData {
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
    @JsonAlias("EXCHANGE")
    private String exchange;
}
