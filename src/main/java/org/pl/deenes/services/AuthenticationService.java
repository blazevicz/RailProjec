package org.pl.deenes.services;

import lombok.RequiredArgsConstructor;
import org.pl.deenes.cfg.JwtService;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.infrastructure.repositories.TokenRepository;
import org.pl.deenes.model.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final DriverRepository driverRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    public AuthenticationResponse register(RegisterRequest request) {
        Driver driver = Driver.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .password(passwordEncoder.encode(request.getPassword()))
                .pesel(request.getPesel())
                .active(request.getActive())
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
        var tokenEntities =
                tokenRepository
                        .findAllByDriverEntity_DriverIdAndExpiredIsFalseAndRevokedIsFalse(
                                user.getDriverId());

        if (tokenEntities.isEmpty()) {
            return;
        }
        tokenEntities.forEach(tokenEntity -> {
            tokenEntity.setExpired(true);
            tokenEntity.setRevoked(true);
        });
        tokenRepository.saveAll(tokenEntities);
    }

    private void saveUserToken(Driver driver, String jwtToken) {

        var token = Token.builder()
                .driver(driver)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
