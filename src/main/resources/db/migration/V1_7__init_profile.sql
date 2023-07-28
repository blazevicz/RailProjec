CREATE TABLE terrain_profile
(
    terrain_profile_id SERIAL PRIMARY KEY,
    kilometer          INTEGER,
    height             INTEGER,
    slope              DECIMAL,
    line_id INT,

    CONSTRAINT fk_terrain_profile_line
        FOREIGN KEY (line_id)
            REFERENCES line (line_id)
);
