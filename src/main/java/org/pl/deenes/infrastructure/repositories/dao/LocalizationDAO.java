package org.pl.deenes.infrastructure.repositories.dao;


import org.pl.deenes.model.Localization;

public interface LocalizationDAO {

    Localization findByStation(String stationName);
}
