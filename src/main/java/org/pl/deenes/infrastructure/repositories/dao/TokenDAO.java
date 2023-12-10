package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Token;

import java.util.List;
import java.util.Optional;

public interface TokenDAO {
    Optional<Token> findByToken(String token);
    List<Token> findAllByDriverEntity_DriverIdAndExpiredIsFalseAndRevokedIsFalse(Integer id);
    Token save(Token token);
    List<Token> saveAll(List<Token> tokens);
}