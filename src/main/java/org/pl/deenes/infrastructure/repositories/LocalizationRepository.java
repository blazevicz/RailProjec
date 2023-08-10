package org.pl.deenes.infrastructure.repositories;


import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.LocalizationEntity;
import org.pl.deenes.infrastructure.mapper.LocalizationMapper;
import org.pl.deenes.infrastructure.repositories.dao.LocalizationDAO;
import org.pl.deenes.infrastructure.repositories.jpa.LocalizationJpaRepository;
import org.pl.deenes.model.Localization;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LocalizationRepository implements LocalizationDAO {

    private final LocalizationJpaRepository localizationJpaRepository;
    private final LocalizationMapper localizationMapper;

    @Override
    public Localization findByStation(String stationName) {
        LocalizationEntity byStation = localizationJpaRepository.findByStation(stationName);
        return localizationMapper.mapFromEntity(byStation);
    }
}
