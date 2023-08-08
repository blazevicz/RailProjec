CREATE TABLE caution
(
    caution_id   SERIAL PRIMARY KEY,
    line_number  INTEGER,
    start_from   DOUBLE PRECISION,
    end_on       DOUBLE PRECISION,
    reason       VARCHAR(255),
    track_number VARCHAR(50),
    left_track   VARCHAR(50),
    right_track  VARCHAR(50),
    comments     VARCHAR(999)
);
