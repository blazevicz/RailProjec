package org.pl.deenes.infrastructure.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.DispatcherEntity;
import org.pl.deenes.infrastructure.mapper.DispatcherMapper;
import org.pl.deenes.infrastructure.repositories.jpa.DispatcherJpaRepository;
import org.pl.deenes.model.Dispatcher;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        Dispatcher dispatcher = new Dispatcher(
                1, "A", "B", "123", true, Collections.emptySet(), "asd", 66071749125L, Collections.emptyList());
        DispatcherEntity entity = new DispatcherEntity(1, "A", "B", "123", "asd", true, Collections.emptySet());

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
        DispatcherEntity entity = new DispatcherEntity();

        when(dispatcherJpaRepository.findBySurname(surname)).thenReturn(Optional.of(entity));

        Optional<DispatcherEntity> result = dispatcherRepository.findBySurname(surname);

        assertEquals(Optional.of(entity), result);
        verify(dispatcherJpaRepository, times(1)).findBySurname(surname);
    }

    @Test
    void findAll() {
        List<DispatcherEntity> entities = List.of(new DispatcherEntity(), new DispatcherEntity());
        List<Dispatcher> dispatchers = List.of(new Dispatcher(
                        1, "A", "B", "123", true, Set.of(), "asd", 66071749125L, List.of()),
                new Dispatcher(
                        2, "A", "B", "1234", true, Set.of(), "asd", 16071749125L, List.of()));

        when(dispatcherJpaRepository.findAll()).thenReturn(entities);
        when(dispatcherMapper.mapFromEntity(any(DispatcherEntity.class))).thenReturn(new Dispatcher(
                        1, "A", "B", "123", true, Set.of(), "asd", 66071749125L, List.of()))
                .thenReturn(new Dispatcher(
                        2, "A", "B", "1234", true, Set.of(), "asd", 16071749125L, List.of()));

        List<Dispatcher> result = dispatcherRepository.findAll();

        assertEquals(dispatchers, result);
        verify(dispatcherJpaRepository, times(1)).findAll();
        verify(dispatcherMapper, times(entities.size())).mapFromEntity(any(DispatcherEntity.class));
    }
}
