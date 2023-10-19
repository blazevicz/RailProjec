package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.TokenEntity;
import org.pl.deenes.infrastructure.mapper.TokenMapper;
import org.pl.deenes.infrastructure.repositories.dao.TokenDAO;
import org.pl.deenes.infrastructure.repositories.jpa.TokenJpaRepository;
import org.pl.deenes.model.Token;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TokenRepository implements TokenDAO {

    private final TokenMapper tokenMapper;
    private final TokenJpaRepository tokenJpaRepository;

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenJpaRepository.findByToken(token)
                .map(tokenMapper::mapFromEntity);
    }

    @Override
    public List<Token> findAllByDriverEntity_DriverIdAndExpiredIsFalseAndRevokedIsFalse(Integer id) {
        List<TokenEntity> tokens =
                tokenJpaRepository.findAllByDriverEntity_DriverIdAndExpiredIsFalseAndRevokedIsFalse(id);
        return tokens.stream().map(tokenMapper::mapFromEntity).toList();
    }

    @Override
    public Token save(Token token) {
        TokenEntity save = tokenJpaRepository.save(tokenMapper.mapToEntity(token));
        return tokenMapper.mapFromEntity(save);
    }

    @Override
    public List<Token> saveAll(List<Token> tokens) {
        List<TokenEntity> list = tokens.stream().map(tokenMapper::mapToEntity).toList();
        List<TokenEntity> tokenEntities = tokenJpaRepository.saveAll(list);
        return tokenEntities.stream().map(tokenMapper::mapFromEntity).toList();
    }
}
