CREATE TABLE driver_role
(
    driver_id INT NOT NULL,
    role_id   INT NOT NULL,
    PRIMARY KEY (driver_id, role_id),
    CONSTRAINT fk_driver_role_driver
        FOREIGN KEY (driver_id)
            REFERENCES driver (driver_id),
    CONSTRAINT fk_driver_role
        FOREIGN KEY (role_id)
            REFERENCES role (role_id)
);

CREATE TABLE dispatcher_role
(
    dispatcher_id INT NOT NULL,
    role_id       INT NOT NULL,
    PRIMARY KEY (dispatcher_id, role_id),
    CONSTRAINT fk_dispatcher_role_dispatcher
        FOREIGN KEY (dispatcher_id)
            REFERENCES dispatcher (dispatcher_id),
    CONSTRAINT fk_dispatcher_role
        FOREIGN KEY (role_id)
            REFERENCES role (role_id)
);