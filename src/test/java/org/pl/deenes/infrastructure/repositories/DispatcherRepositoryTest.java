package org.pl.deenes.infrastructure.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.DispatcherEntity;
import org.pl.deenes.infrastructure.mapper.DispatcherMapper;
import org.pl.deenes.infrastructure.repositories.jpa.DispatcherJpaRepository;
import org.pl.deenes.model.Dispatcher;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DispatcherRepositoryTest {
    @Mock
    private DispatcherJpaRepository dispatcherJpaRepository;

    @Mock
    private DispatcherMapper dispatcherMapper;

    @InjectMocks
    private DispatcherRepository dispatcherRepository;

    @Test
    void testSave2() {
        Dispatcher dispatcher = Dispatcher.builder()
                .id(1)
                .name("A")
                .surname("B")
                .phoneNumber("66071749125L")
                .password("asd")
                .active(true)
                .roles(Collections.emptySet())
                .build();
        DispatcherEntity entity = DispatcherEntity.builder()
                .name("A")
                .surname("B")
                .phoneNumber("66071749125L")
                .password("asd")
                .active(true)
                .roles(Collections.emptySet())
                .build();

        when(dispatcherMapper.mapToEntity(dispatcher)).thenReturn(entity);
        when(dispatcherMapper.mapFromEntity(entity)).thenReturn(dispatcher);
        when(dispatcherJpaRepository.save(entity)).thenReturn(entity);

        Dispatcher savedDispatcher = dispatcherRepository.save(dispatcher);

        assertEquals(dispatcher, savedDispatcher);

        verify(dispatcherMapper, times(1)).mapToEntity(dispatcher);
        verify(dispatcherMapper, times(1)).mapFromEntity(entity);
        verify(dispatcherJpaRepository, times(1)).save(entity);
    }

    @Test
    void findBySurname() {
        String surname = "Adamiak";
        DispatcherEntity dispatcherEntity = new DispatcherEntity();
        Dispatcher dispatcher = Dispatcher.builder().build();

        when(dispatcherJpaRepository.findBySurname(surname)).thenReturn(Optional.of(dispatcherEntity));
        when(dispatcherMapper.mapFromEntity(dispatcherEntity)).thenReturn(dispatcher);

        Optional<Dispatcher> result = dispatcherRepository.findBySurname(surname);

        assertEquals(Optional.of(dispatcher), result);
        Mockito.verify(dispatcherJpaRepository, Mockito.times(1)).findBySurname(surname);
        Mockito.verify(dispatcherMapper, Mockito.times(1)).mapFromEntity(dispatcherEntity);
    }


    @Test
    void findAll() {
        List<DispatcherEntity> entities = List.of(new DispatcherEntity(), new DispatcherEntity());
        List<Dispatcher> dispatchers = List.of(Dispatcher.builder()
                        .id(1)
                        .name("A")
                        .surname("B")
                        .phoneNumber("66071749125L")
                        .password("asd")
                        .active(true)
                        .roles(Collections.emptySet())
                        .build(),
                Dispatcher.builder()
                        .id(2)
                        .name("A")
                        .surname("C")
                        .phoneNumber("66271749125L")
                        .password("asd")
                        .active(true)
                        .roles(Collections.emptySet())
                        .build());

        when(dispatcherJpaRepository.findAll()).thenReturn(entities);
        when(dispatcherMapper.mapFromEntity(any(DispatcherEntity.class))).thenReturn(Dispatcher.builder()
                        .id(1)
                        .name("A")
                        .surname("B")
                        .phoneNumber("66071749125L")
                        .password("asd")
                        .active(true)
                        .roles(Collections.emptySet())
                        .build())
                .thenReturn(Dispatcher.builder()
                        .id(2)
                        .name("A")
                        .surname("C")
                        .phoneNumber("66271749125L")
                        .password("asd")
                        .active(true)
                        .roles(Collections.emptySet())
                        .build());

        List<Dispatcher> result = dispatcherRepository.findAll();

        assertEquals(dispatchers, result);
        verify(dispatcherJpaRepository, times(1)).findAll();
        verify(dispatcherMapper, times(entities.size())).mapFromEntity(any(DispatcherEntity.class));
    }
}
