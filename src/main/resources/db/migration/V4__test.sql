ALTER TABLE zlk
    ADD CONSTRAINT uq_zlk_region_wos UNIQUE (zlkRegionNumber, actualWOS);
