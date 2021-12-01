CREATE TABLE IF NOT EXISTS products
(
    id         uuid PRIMARY KEY NOT NULL,
    name       VARCHAR(25),
    ticker     VARCHAR(10),
    quantity   INT,
    created_at TIMESTAMP        NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    id         uuid PRIMARY KEY NOT NULL,
    balance    DECIMAL(12, 2)   NOT NULL, -- default value???
    created_at TIMESTAMP        NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction
(
    transaction_id uuid PRIMARY KEY NOT NULL,
    status         VARCHAR(3),
    created_at     TIMESTAMP        NOT NULL
);

CREATE TABLE IF NOT EXISTS portfolio
(
    id          uuid PRIMARY KEY NOT NULL,
    name        VARCHAR(30),
    created_at  TIMESTAMP,
    products_id uuid             NOT NULL,
    CONSTRAINT fk_item_portfolio
        FOREIGN KEY (products_id)
            REFERENCES portfolio (id)
--             ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS orders
(
    id             uuid PRIMARY KEY NOT NULL,
    quantity       INT,
    price          DECIMAL(12, 2)   NOT NULL,
    side           VARCHAR(10),
    created_at     TIMESTAMP,
    transaction_id uuid             NOT NULL,
    CONSTRAINT fk_order_transaction
        FOREIGN KEY (transaction_id)
            REFERENCES orders (id)
);

CREATE TABLE IF NOT EXISTS clients
(
    id           uuid PRIMARY KEY NOT NULL,
    first_name   VARCHAR(25),
    last_name    VARCHAR(30),
    username     VARCHAR(20) UNIQUE,
    email        VARCHAR(50) UNIQUE,
    password     VARCHAR(100),
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
        REFERENCES orders (id)
);
