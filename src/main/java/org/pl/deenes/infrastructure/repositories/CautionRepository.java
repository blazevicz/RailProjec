package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.pl.deenes.infrastructure.entity.CautionEntity;
import org.pl.deenes.infrastructure.mapper.CautionMapper;
import org.pl.deenes.infrastructure.repositories.dao.CautionDAO;
import org.pl.deenes.infrastructure.repositories.jpa.CautionJpaRepository;
import org.pl.deenes.model.Caution;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CautionRepository implements CautionDAO {
    private final CautionMapper cautionMapper;
    private final CautionJpaRepository cautionJpaRepository;

    @Override
    public List<CautionEntity> saveAll(@NonNull List<Caution> cautionList) {
        var cautionEntityList = cautionList.stream().map(cautionMapper::mapToEntity).toList();
        return cautionJpaRepository.saveAll(cautionEntityList);
    }
}
