package org.pl.deenes.infrastructure.repositories;


import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.LineDetailsEntity;
import org.pl.deenes.infrastructure.mapper.LineDetailsMapper;
import org.pl.deenes.infrastructure.repositories.dao.LineDAO;
import org.pl.deenes.model.LineDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@AllArgsConstructor
public class LineRepository implements LineDAO {
    private final LineDetailsMapper lineDetailsMapper;
    private final LineDetailsRepository lineDetailsRepository;


    @Override
    public void saveALl(List<LineDetails> lineDetails) {
        List<LineDetailsEntity> collect = lineDetails.stream().map(lineDetailsMapper::mapToEntity).collect(toList());
        lineDetailsRepository.saveAll(collect);

    }
}
