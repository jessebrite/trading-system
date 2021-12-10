CREATE TABLE IF NOT EXISTS market_data
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    last_traded_price DOUBLE PRECISION,
    ask_price DOUBLE PRECISION NOT NULL,
    bid_price DOUBLE PRECISION NOT NULL,
    buy_limit DOUBLE PRECISION NOT NULL,
    max_price_shift DOUBLE PRECISION NOT NULL,
    sell_limit DOUBLE PRECISION NOT NULL,
    ticker VARCHAR,
    timestamp BIGINT,
    exchange VARCHAR
)