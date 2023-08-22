CREATE TABLE driver
(
    driver_id SERIAL PRIMARY KEY,
    name     VARCHAR(25)  NOT NULL,
    surname  VARCHAR(25)  NOT NULL,
    pesel    VARCHAR(11)  NOT NULL unique,
    active   BOOLEAN      NOT NULL,
    password VARCHAR(256) NOT NULL,
    line_id   INT
);
