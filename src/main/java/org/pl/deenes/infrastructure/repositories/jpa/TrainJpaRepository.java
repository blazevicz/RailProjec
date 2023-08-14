package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainJpaRepository extends JpaRepository<TrainEntity, Integer> {

    List<TrainEntity> findByTrainKwr(Integer trainKwr);

}
