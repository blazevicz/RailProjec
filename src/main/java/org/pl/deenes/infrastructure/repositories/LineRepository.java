package org.pl.deenes.infrastructure.repositories;


import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.LineDetailsEntity;
import org.pl.deenes.infrastructure.mapper.LineDetailsMapper;
import org.pl.deenes.model.LineDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class LineRepository implements LineRepositoryDAO {
    private final LineDetailsMapper lineDetailsMapper;
    private final LineEntryRepository lineEntryRepository;


    @Override
    public void saveALl(List<LineDetails> lineDetails) {
        List<LineDetailsEntity> collect = lineDetails.stream().map(lineDetailsMapper::mapToEntity).collect(Collectors.toList());
        lineEntryRepository.saveAll(collect);

    }
}
