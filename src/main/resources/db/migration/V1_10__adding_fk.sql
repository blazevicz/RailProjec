CREATE TYPE locomotive_type AS ENUM (
    'ET22',
    'ET41',
    'ET42',
    'ET43',
    'EU07',
    'EU46',
    'SM42',
    'SM31',
    'ST48');

ALTER TABLE driver
    ADD CONSTRAINT fk_driver_line FOREIGN KEY (line_id) REFERENCES line (line_id),
    ADD COLUMN locomotive locomotive_type;

ALTER TABLE line
    ADD CONSTRAINT fk_line FOREIGN KEY (line_details_id) REFERENCES line_details (line_details_id),
    ADD CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES region (region_id);

ALTER TABLE line_details
    ADD CONSTRAINT fk_terrain_profile FOREIGN KEY (terrain_profile_id) REFERENCES terrain_profile (terrain_profile_id);

ALTER TABLE train_analyse
    ADD CONSTRAINT fk_train_analyse_stats
        FOREIGN KEY (train_stats_id)
            REFERENCES train_stats (train_stats_id),
    ADD CONSTRAINT fk_train
        FOREIGN KEY (train_id)
            REFERENCES train (train_id);

ALTER TABLE train_stats
    ADD CONSTRAINT fk_train
        FOREIGN KEY (train_id)
            REFERENCES train (train_id);

