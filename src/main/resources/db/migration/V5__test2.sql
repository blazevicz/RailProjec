ALTER TABLE line_entry
    ADD CONSTRAINT fk_line_entry_zlk
        FOREIGN KEY (zlkregionnumber, actualWOS)
            REFERENCES zlk (zlkRegionNumber, actualWOS);
