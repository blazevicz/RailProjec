CREATE TABLE line_details
(
    line_details_id    SERIAL PRIMARY KEY,
    start_station      VARCHAR(255),
    end_station        VARCHAR(255),
    line_number        INTEGER,
    start_kilometer    DOUBLE PRECISION,
    end_kilometer      DOUBLE PRECISION,
    page               INTEGER,
    railway_region     INTEGER,
    terrain_profile_id INT
);
