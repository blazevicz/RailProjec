package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalyseJpaRepository extends JpaRepository<AnalyseEntity, Integer> {


    Optional<AnalyseEntity> findByTrainKwr(Integer trainKwr);
}
