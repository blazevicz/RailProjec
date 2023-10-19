package org.pl.deenes.api.controller.dto;

import lombok.Data;
import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.pl.deenes.model.TokenType;

@Data
public class TokenDTO {

    private Integer id;
    private String token;
    private TokenType tokenType = TokenType.BEARER;
    private boolean revoked;
    private boolean expired;
    private DriverEntity driverEntity;
}
