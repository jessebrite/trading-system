CREATE TABLE IF NOT EXISTS portfolio
(
    id          uuid PRIMARY KEY NOT NULL,
    created_at  TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products
(
    id         uuid PRIMARY KEY NOT NULL,
    name       VARCHAR(25),
    ticker     VARCHAR(10),
    quantity   INT,
    created_at TIMESTAMP        NOT NULL,
        portfolio_id uuid             NOT NULL,
    CONSTRAINT fk_item_portfolio
        FOREIGN KEY (portfolio_id)
            REFERENCES portfolio (id)
--             ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS accounts
(
    id         uuid PRIMARY KEY NOT NULL,
    balance    DECIMAL(12, 2)   NOT NULL,
    created_at TIMESTAMP        NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions
(
    id uuid PRIMARY KEY NOT NULL,
    status         VARCHAR(3),
    created_at     TIMESTAMP        NOT NULL

);

CREATE TABLE IF NOT EXISTS order_table
(
    id             uuid PRIMARY KEY NOT NULL,
    quantity       INT,
    price          DECIMAL(12, 2)   NOT NULL,
    side           VARCHAR(10),
    created_at     TIMESTAMP,
    transaction_id uuid             NOT NULL,
    CONSTRAINT fk_order_transaction
        FOREIGN KEY (transaction_id)
            REFERENCES transactions (id)
);

DROP TABLE IF EXISTS clients;

CREATE TABLE IF NOT EXISTS clients
(
    id           uuid PRIMARY KEY NOT NULL,
    first_name   VARCHAR(25),
    last_name    VARCHAR(30),
    username     VARCHAR(20) UNIQUE,
    email        VARCHAR(50) UNIQUE,
    password     VARCHAR(100),
    role VARCHAR(20),
    created_at   TIMESTAMP        NOT NULL,
    portfolio_id uuid             NOT NULL,
    CONSTRAINT fk_client_portfolio
        FOREIGN KEY (portfolio_id)
            REFERENCES portfolio (id),
    account_id   uuid             NOT NULL,
    CONSTRAINT fk_client_account
        FOREIGN KEY (account_id)
            REFERENCES accounts (id),
    order_id     uuid,
    CONSTRAINT fk_client_order FOREIGN KEY (order_id)
        REFERENCES order_table (id)
);


CREATE TABLE IF NOT EXISTS market_data
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    last_traded_price DOUBLE PRECISION NOT NULL,
    ask_price DOUBLE PRECISION NOT NULL,
    bid_price DOUBLE PRECISION NOT NULL,
    buy_limit DOUBLE PRECISION NOT NULL,
    max_price_shift DOUBLE PRECISION NOT NULL,
    sell_limit DOUBLE PRECISION NOT NULL,
    ticker VARCHAR,
    timestamp BIGINT,
    exchange VARCHAR
);