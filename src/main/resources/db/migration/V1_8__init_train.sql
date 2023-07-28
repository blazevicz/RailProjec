CREATE TABLE train
(
    train_id         SERIAL PRIMARY KEY,
    train_kwr        INTEGER unique,
    company_name     VARCHAR(64),
    date_plan        date,
    road_stats       decimal,
    driver_id        INT,
    train_analyse_id INT,

    CONSTRAINT fk_train_driver
        FOREIGN KEY (driver_id)
            REFERENCES driver (driver_id),
    CONSTRAINT fk_train_analyse
        FOREIGN KEY (train_analyse_id)
            REFERENCES train_analyse (train_analyse_id)
);
