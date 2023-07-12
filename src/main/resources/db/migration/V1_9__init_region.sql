CREATE TABLE region
(
    region_id SERIAL PRIMARY KEY,
    region    INTEGER,
    wos       VARCHAR(16),
    wos_link  VARCHAR(128),
    line_id   INT,

    CONSTRAINT fk_region_line
        FOREIGN KEY (line_id)
            REFERENCES line (line_id)
);
