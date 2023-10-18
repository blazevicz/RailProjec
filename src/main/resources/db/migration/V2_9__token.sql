CREATE TABLE token
(
    id         SERIAL PRIMARY KEY,
    token      VARCHAR(255) UNIQUE,
    token_type VARCHAR(50),
    revoked    BOOLEAN,
    expired    BOOLEAN,
    driver_id  INTEGER,
    FOREIGN KEY (driver_id) REFERENCES driver (driver_id)
);
