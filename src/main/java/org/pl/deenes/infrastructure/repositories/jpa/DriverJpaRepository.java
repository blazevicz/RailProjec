package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverJpaRepository extends JpaRepository<DriverEntity, Integer> {

    Optional<DriverEntity> findByPesel(String pesel);

}
