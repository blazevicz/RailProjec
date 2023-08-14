package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainStatsJPaRepository extends JpaRepository<TrainStatsEntity, Integer> {

    //Optional<TrainStatsEntity> findByAnalyse_TrainAnalyseId(Integer id);

    List<TrainStatsEntity> findAllByTrainEntity(Integer trainId);

}
