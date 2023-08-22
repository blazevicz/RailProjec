CREATE TABLE dispatcher
(
    dispatcher_id SERIAL PRIMARY KEY,
    name         VARCHAR(25)  NOT NULL,
    surname      VARCHAR(25)  NOT NULL,
    password     VARCHAR(256) NOT NULL,
    active       BOOLEAN      NOT NULL,
    phone_number VARCHAR(25)  NOT NULL unique
);
