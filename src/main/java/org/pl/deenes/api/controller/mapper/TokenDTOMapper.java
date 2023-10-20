package org.pl.deenes.api.controller.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.pl.deenes.api.controller.dto.TokenDTO;
import org.pl.deenes.model.Token;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TokenDTOMapper {
    TokenDTO mapToDTO(Token model);

    Token mapFromDTO(TokenDTO dto);
}
