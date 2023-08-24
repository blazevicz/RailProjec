package org.pl.deenes.infrastructure.repositories.dao;


import org.pl.deenes.model.Localization;

import java.util.Optional;

public interface LocalizationDAO {
    Optional<Localization> findByStation(String stationName);
}
