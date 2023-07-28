CREATE TABLE line
(
    line_id         SERIAL PRIMARY KEY,
    line_number INT NOT NULL unique,
    region_id       INT,
    line_details_id INT,
    relation_from   varchar(64),
    relation_to     varchar(64)
);
