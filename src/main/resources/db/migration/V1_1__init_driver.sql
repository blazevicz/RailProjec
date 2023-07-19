CREATE TABLE driver
(
    driver_id SERIAL PRIMARY KEY,
    name      VARCHAR(25) NOT NULL,
    surname   VARCHAR(25) NOT NULL,
    pesel     INT         NOT NULL,
    line_id   INT
);
