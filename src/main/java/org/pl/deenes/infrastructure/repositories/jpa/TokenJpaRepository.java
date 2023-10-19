package org.pl.deenes.infrastructure.repositories.jpa;

import org.pl.deenes.infrastructure.entity.TokenEntity;
import org.pl.deenes.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenJpaRepository extends JpaRepository<TokenEntity, Integer> {

    Optional<TokenEntity> findByToken(String token);

    List<TokenEntity> findAllByDriverEntity_DriverIdAndExpiredIsFalseAndRevokedIsFalse(Integer id);

    TokenEntity save(Token token);
}