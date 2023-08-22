package org.pl.deenes.infrastructure.repositories;


import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.LocalizationEntity;
import org.pl.deenes.infrastructure.mapper.LocalizationMapper;
import org.pl.deenes.infrastructure.repositories.dao.LocalizationDAO;
import org.pl.deenes.infrastructure.repositories.jpa.LocalizationJpaRepository;
import org.pl.deenes.model.Localization;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class LocalizationRepository implements LocalizationDAO {

    private final LocalizationJpaRepository localizationJpaRepository;
    private final LocalizationMapper localizationMapper;

    @Override
    public Optional<Localization> findByStation(String stationName) {
        LocalizationEntity byStation = localizationJpaRepository.findByStation(stationName);
        return Optional.ofNullable(localizationMapper.mapFromEntity(byStation));
    }
}
