package org.pl.deenes.infrastructure.mapper;


import org.mapstruct.Mapper;
import org.pl.deenes.infrastructure.entity.TokenEntity;
import org.pl.deenes.model.Token;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    Token mapFromEntity(TokenEntity entity);

    TokenEntity mapToEntity(Token model);
}
