package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.infrastructure.mapper.TrainMapper;
import org.pl.deenes.infrastructure.mapper.TrainStatsMapper;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.pl.deenes.infrastructure.repositories.jpa.TrainJpaRepository;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Slf4j
@AllArgsConstructor
public class TrainRepository implements TrainDAO {

    private final TrainJpaRepository trainJpaRepository;
    private final TrainMapper trainMapper;
    private final TrainStatsMapper trainStatsMapper;


    @Override
    public Train save(Train train) {
        TrainEntity trainEntity = trainMapper.mapToEntity(train);
        Set<TrainStats> trainStats = train.getTrainStats();
        Set<TrainStatsEntity> collect = trainStats.stream().map(trainStatsMapper::mapToEntity).collect(Collectors.toSet());
        collect.forEach(a -> a.setTrainEntity(trainEntity));

        trainEntity.setTrainStats(collect);


        TrainEntity saved = trainJpaRepository.save(trainEntity);
        return trainMapper.mapFromEntity(saved);
    }

    @Override
    @Transactional
    public List<Train> find(Integer trainKwr) {
        return trainJpaRepository.findByTrainKwr(trainKwr).stream()
                .map(a -> trainMapper.mapFromEntity(a)).toList();
    }

    @Override
    public void delete(Integer trainKwr) {
        var byTrainKwr = trainJpaRepository.findByTrainKwr(trainKwr);
        //trainJpaRepository.delete(byTrainKwr);
    }


    @Override
    public List<Train> findAll() {
        List<TrainEntity> all = trainJpaRepository.findAll();
        return all.stream().map(a -> trainMapper.mapFromEntity(a)).toList();
    }


}
