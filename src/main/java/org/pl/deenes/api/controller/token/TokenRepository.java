package org.pl.deenes.api.controller.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByToken(String token);

    List<Token> findAllByDriverEntity_DriverIdAndExpiredIsFalseAndRevokedIsFalse(Integer id);
}