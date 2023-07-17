package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainJpaRepository extends JpaRepository<TrainEntity, Integer> {

    Optional<TrainEntity> findByTrainKwr(Integer trainKwr);

}
