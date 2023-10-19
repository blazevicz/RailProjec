package org.pl.deenes.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {

    private Integer id;
    private String token;
    private TokenType tokenType = TokenType.BEARER;
    private boolean revoked;
    private boolean expired;
    private Driver driver;
}
