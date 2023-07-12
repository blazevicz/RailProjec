CREATE TABLE line
(
    line_id         SERIAL PRIMARY KEY,
    line_number     SMALLINT NOT NULL UNIQUE,
    size            REAL,
    region_id       INT,
    train_stats_id  INT,
    line_details_id INT
);
