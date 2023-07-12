CREATE TABLE terrain_profile
(
    terrain_profile_id SERIAL PRIMARY KEY,
    kilometer          INTEGER,
    height             INTEGER,
    slope              DECIMAL,
    line_details_id    INT,

    CONSTRAINT fk_terrain_profile_line
        FOREIGN KEY (line_details_id)
            REFERENCES line_details (line_details_id)
);
