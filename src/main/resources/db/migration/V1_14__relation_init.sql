CREATE TABLE analyse_train_stats
(
    analyse_train_stats_id SERIAL PRIMARY KEY,
    train_analyse_id       INT,
    train_stats_id         INT,
    FOREIGN KEY (train_analyse_id) REFERENCES train_analyse (train_analyse_id),
    FOREIGN KEY (train_stats_id) REFERENCES train_stats (train_stats_id)
);