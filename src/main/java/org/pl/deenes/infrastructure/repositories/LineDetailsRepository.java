package org.pl.deenes.infrastructure.repositories;

import org.pl.deenes.infrastructure.entity.LineDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineDetailsRepository extends JpaRepository<LineDetailsEntity, Integer> {


}
