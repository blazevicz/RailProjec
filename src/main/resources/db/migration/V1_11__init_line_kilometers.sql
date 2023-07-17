CREATE TABLE line_kilometers
(
    line_id    INT,
    kilometers DOUBLE PRECISION,
    FOREIGN KEY (line_id) REFERENCES line (line_id)
);
