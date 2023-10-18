package org.pl.deenes.api.controller;

import lombok.RequiredArgsConstructor;
import org.pl.deenes.api.controller.token.Token;
import org.pl.deenes.api.controller.token.TokenRepository;
import org.pl.deenes.api.controller.token.TokenType;
import org.pl.deenes.cfg.JwtService;
import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.pl.deenes.infrastructure.mapper.DriverMapper;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.model.Driver;
import org.pl.deenes.model.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final DriverRepository driverRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final DriverMapper driverMapper;

    public AuthenticationResponse register(RegisterRequest request) {
        Driver driver = Driver.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .password(passwordEncoder.encode(request.getPassword()))
                .pesel(request.getPesel())
                .active(request.getActive())
                .roles(Set.of(Role.builder().role("DRIVER").build()))
                .build();
        Driver save = driverRepository.save(driver);
        var jwtToken = jwtService.generateToken(driver);
        saveUserToken(save, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getSurname(),
                request.getPassword()
        ));
        var user = driverRepository.findBySurname(request.getSurname())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(Driver user) {
        List<Token> tokens =
                tokenRepository.findAllByDriverEntity_DriverIdAndExpiredIsFalseAndRevokedIsFalse(user.getDriverId());

        if (tokens.isEmpty()) {
            return;
        }
        tokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(tokens);
    }

    private void saveUserToken(Driver driver, String jwtToken) {
        DriverEntity driverEntity = driverMapper.mapToEntity(driver);

        var token = Token.builder()
                .driverEntity(driverEntity)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
