package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.infrastructure.mapper.TrainStatsMapper;
import org.pl.deenes.infrastructure.repositories.dao.TrainStatsDAO;
import org.pl.deenes.infrastructure.repositories.jpa.TrainStatsJPaRepository;
import org.pl.deenes.model.TrainStats;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TrainStatsRepository implements TrainStatsDAO {
    private final TrainStatsJPaRepository trainStatsJPaRepository;
    private final TrainStatsMapper trainStatsMapper;

    @Override
    public Optional<TrainStats> findAllByTrainStatsId(Integer id) {
        return trainStatsJPaRepository.findById(id).map(trainStatsMapper::mapFromEntity);
    }

    @Override
    public List<TrainStats> findAll() {
        List<TrainStatsEntity> all = trainStatsJPaRepository.findAll();
        return all.stream().map(trainStatsMapper::mapFromEntity).toList();
    }
}
