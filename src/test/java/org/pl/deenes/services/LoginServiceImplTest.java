package org.pl.deenes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.DispatcherEntity;
import org.pl.deenes.infrastructure.entity.RoleEntity;
import org.pl.deenes.infrastructure.repositories.DispatcherRepository;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    DispatcherRepository dispatcherRepository;

    @Mock
    DriverRepository driverRepository;

    @InjectMocks
    LoginServiceImpl loginService;

    @Test
    void loadUserByUsername() {
        DispatcherEntity dispatcherEntity = DispatcherEntity.builder()
                .surname("A")
                .password("asd")
                .roles(Set.of(RoleEntity.builder().role("DISPATCHER").build()))
                .active(true).build();

        when(dispatcherRepository.findBySurname(anyString())).thenReturn(Optional.of(dispatcherEntity));

        UserDetails userDetails = loginService.loadUserByUsername("A");

        Assertions.assertEquals(dispatcherEntity.getSurname(), userDetails.getUsername());
        Assertions.assertEquals(dispatcherEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(dispatcherEntity.getActive(), userDetails.isEnabled());
    }

    @Test
    void notFoundUserWhenLoadingByUsername() {

        when(dispatcherRepository.findBySurname(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> loginService.loadUserByUsername(anyString()),
                "Expected UsernameNotFoundException to be thrown");


    }
}