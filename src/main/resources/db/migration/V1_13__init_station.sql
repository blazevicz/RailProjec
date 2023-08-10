CREATE TABLE localization
(
    localization_id SERIAL PRIMARY KEY,
    station         VARCHAR(99),
    latitude        DOUBLE PRECISION,
    longitude       DOUBLE PRECISION
);
