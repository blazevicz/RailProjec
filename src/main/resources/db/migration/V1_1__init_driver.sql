CREATE TABLE driver
(
    driver_id    SERIAL PRIMARY KEY,
    name         VARCHAR(25) NOT NULL,
    surname      VARCHAR(25) NOT NULL,
    phone_number VARCHAR(25) NOT NULL,
    line_id      INT
);
