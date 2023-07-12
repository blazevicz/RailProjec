CREATE TABLE train_analyse
(
    train_analyse_id SERIAL PRIMARY KEY,
    train_number     SMALLINT    NOT NULL,
    train_kwr        SMALLINT    NOT NULL UNIQUE,
    train_max_weight SMALLINT    NOT NULL,
    train_max_length SMALLINT    NOT NULL,
    start_station    VARCHAR(60) NOT NULL,
    end_station      VARCHAR(60) NOT NULL,
    train_type       VARCHAR(15) NOT NULL,
    locomotive_type  VARCHAR(15) NOT NULL,
    train_max_speed  SMALLINT    NOT NULL,
    brake_percent    SMALLINT    NOT NULL,
    train_id         INT,
    train_stats_id   INT
);
