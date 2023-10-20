package org.pl.deenes.infrastructure.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.pl.deenes.infrastructure.entity.TokenEntity;
import org.pl.deenes.model.Token;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TokenMapper {
    Token mapFromEntity(TokenEntity entity);

    TokenEntity mapToEntity(Token model);
}
