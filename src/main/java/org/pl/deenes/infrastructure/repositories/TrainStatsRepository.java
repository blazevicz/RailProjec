package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.mapper.TrainStatsMapper;
import org.pl.deenes.infrastructure.repositories.dao.TrainStatsDAO;
import org.pl.deenes.infrastructure.repositories.jpa.TrainStatsJPaRepository;
import org.pl.deenes.model.TrainStats;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class TrainStatsRepository implements TrainStatsDAO {
    private final TrainStatsJPaRepository trainStatsJPaRepository;
    private final TrainStatsMapper trainStatsMapper;

    @Override
    public Optional<TrainStats> findAllByTrainStatsId(Integer id) {
        return Optional.empty();
    }


/*    @Override
    public Optional<TrainStats> findAllByTrainStatsId(Integer id) {
        return trainStatsJPaRepository.findByAnalyse_TrainAnalyseId(id)
                .map(a -> trainStatsMapper.mapFromEntity(a, new CycleAvoidingMappingContext()));

    }*/

}
