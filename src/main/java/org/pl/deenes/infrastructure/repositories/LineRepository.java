package org.pl.deenes.infrastructure.repositories;


import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.pl.deenes.infrastructure.entity.LineDetailsEntity;
import org.pl.deenes.infrastructure.entity.LineEntity;
import org.pl.deenes.infrastructure.mapper.LineDetailsMapper;
import org.pl.deenes.infrastructure.mapper.LineMapper;
import org.pl.deenes.infrastructure.repositories.dao.LineDAO;
import org.pl.deenes.infrastructure.repositories.jpa.LineJpaRepository;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.LineDetails;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
public class LineRepository implements LineDAO {
    private final LineDetailsMapper lineDetailsMapper;
    private final LineDetailsRepository lineDetailsRepository;
    private final LineMapper lineMapper;
    private final LineJpaRepository lineJpaRepository;

    @Override
    public void saveALl(@NonNull List<LineDetails> lineDetails) {
        List<LineDetailsEntity> collect = lineDetails.stream().map(lineDetailsMapper::mapToEntity).toList();
        lineDetailsRepository.saveAll(collect);
    }

    @Override
    public Line findLine(Integer lineNumber) {
        LineEntity byLineNumber = lineJpaRepository.findByLineNumber(lineNumber);
        return lineMapper.mapFromEntity(byLineNumber);

    }

}
