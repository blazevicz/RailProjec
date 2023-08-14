CREATE TABLE train
(
    train_id         SERIAL PRIMARY KEY,
    train_kwr INTEGER,
    company_name     VARCHAR(64),
    date_plan        date,
    road_stats       float4,
    driver_id        INT,
    train_analyse_id INT,
    train_number     INTEGER     NOT NULL,
    train_max_weight INTEGER     NOT NULL,
    train_max_length INTEGER     NOT NULL,
    start_station    VARCHAR(60) NOT NULL,
    end_station      VARCHAR(60) NOT NULL,
    train_type       VARCHAR(15) NOT NULL,
    locomotive_type  VARCHAR(15) NOT NULL,
    train_max_speed  SMALLINT    NOT NULL,
    brake_percent    SMALLINT    NOT NULL,
    train_stats_id   INT,

    CONSTRAINT fk_train_driver
        FOREIGN KEY (driver_id)
            REFERENCES driver (driver_id)

);
