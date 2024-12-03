DROP DATABASE simple_wallet;

CREATE DATABASE simple_wallet;

USE simple_wallet;

CREATE TABLE users
(
    id               VARCHAR(100) NOT NULL PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    email            VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    token            VARCHAR(255),
    token_expired_at BIGINT,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE accounts
(
    id         VARCHAR(100) NOT NULL PRIMARY KEY,
    user_id    VARCHAR(100) NOT NULL,
    name       VARCHAR(100) NOT NULL,
    balance    DECIMAL(19, 2) DEFAULT 0.00,
    created_at TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE categories
(
    id         VARCHAR(100)               NOT NULL PRIMARY KEY,
    user_id    VARCHAR(100),
    name       VARCHAR(100)               NOT NULL,
    type       ENUM ('INCOME', 'EXPENSE') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE transactions
(
    id          VARCHAR(100)               NOT NULL PRIMARY KEY,
    user_id     VARCHAR(100)               NOT NULL,
    account_id  VARCHAR(100)               NOT NULL,
    category_id VARCHAR(100)               NOT NULL,
    type        ENUM ('INCOME', 'EXPENSE') NOT NULL,
    amount      DECIMAL(19, 2)             NOT NULL,
    description TEXT,
    date        TIMESTAMP                  NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);