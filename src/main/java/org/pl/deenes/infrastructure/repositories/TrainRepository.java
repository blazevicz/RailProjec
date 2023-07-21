package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.infrastructure.mapper.TrainMapper;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.pl.deenes.infrastructure.repositories.jpa.TrainJpaRepository;
import org.pl.deenes.model.Train;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TrainRepository implements TrainDAO {
    private final TrainJpaRepository trainJpaRepository;
    private final TrainMapper trainMapper;

    @Override
    public Train save(Train train) {
        TrainEntity trainEntity = trainMapper.mapToEntity(train);
        TrainEntity saved = trainJpaRepository.save(trainEntity);
        return trainMapper.mapFromEntity(saved);
    }

    @Override
    public Optional<Train> find(Integer trainKwr) {
        return trainJpaRepository.findByTrainKwr(trainKwr).map(trainMapper::mapFromEntity);
    }

    @Override
    public void delete(Integer trainKwr) {
        var byTrainKwr = trainJpaRepository.findByTrainKwr(trainKwr).orElseThrow();
        trainJpaRepository.delete(byTrainKwr);
    }

    @Override
    public List<Train> findAll() {
        List<TrainEntity> all = trainJpaRepository.findAll();
        return all.stream().map(trainMapper::mapFromEntity).toList();
    }


}
