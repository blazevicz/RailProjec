package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.CautionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CautionJpaRepository extends JpaRepository<CautionEntity, Integer> {


}
