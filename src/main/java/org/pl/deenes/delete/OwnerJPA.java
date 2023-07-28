package org.pl.deenes.delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerJPA extends JpaRepository<Owner, Integer> {
}
