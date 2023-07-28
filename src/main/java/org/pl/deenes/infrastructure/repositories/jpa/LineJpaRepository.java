package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.LineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineJpaRepository extends JpaRepository<LineEntity, Integer> {

    LineEntity findByLineNumber(Integer integer);
}
