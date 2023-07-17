CREATE TABLE line
(
    line_id         SERIAL PRIMARY KEY,
    line_number     SMALLINT NOT NULL,
    size            REAL,
    region_id       INT,
    train_stats_id  INT,
    line_details_id INT,
    train_id        INT,
    kilometers      double precision[],
    relation_from   varchar(64),
    relation_to     varchar(64)
);
