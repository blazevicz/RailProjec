package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.DispatcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispatcherJpaRepository extends JpaRepository<DispatcherEntity, Integer> {

    Optional<DispatcherEntity> findBySurname(String surname);
}
