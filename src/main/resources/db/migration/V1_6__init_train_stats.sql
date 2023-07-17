CREATE TABLE train_stats
(
    train_stats_id   SERIAL PRIMARY KEY,
    distance         INTEGER,
    line_number      INTEGER,
    line_id          INT,
    train_analyse_id INT,
    first_kilometer  DOUBLE PRECISION,
    last_kilometer   DOUBLE PRECISION,


    CONSTRAINT fk_train_stats_line
        FOREIGN KEY (line_id)
            REFERENCES line (line_id),
    CONSTRAINT fk_train_stats_analyse
        FOREIGN KEY (train_analyse_id)
            REFERENCES train_analyse (train_analyse_id)
);
