package org.pl.deenes.infrastructure.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.TrainEntity;
import org.pl.deenes.infrastructure.mapper.TrainMapper;
import org.pl.deenes.infrastructure.mapper.TrainStatsMapper;
import org.pl.deenes.infrastructure.repositories.jpa.TrainJpaRepository;
import org.pl.deenes.model.Train;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainRepositoryTest {

    @Mock
    private TrainJpaRepository trainJpaRepository;

    @Mock
    private TrainMapper trainMapper;

    @Mock
    private TrainStatsMapper trainStatsMapper;

    @InjectMocks
    private TrainRepository trainRepository;

    @Test
    void shouldFindTrainByTrainKwr() {
        Integer trainKwr = 123;
        TrainEntity trainEntity = new TrainEntity();
        Train train = Train.builder().trainKwr(trainKwr).build();

        when(trainJpaRepository.findByTrainKwr(trainKwr)).thenReturn(Optional.of(trainEntity));
        when(trainMapper.mapFromEntity(trainEntity)).thenReturn(train);

        Optional<Train> result = trainRepository.find(trainKwr);

        assertEquals(train, result.orElse(null));
    }

    @Test
    void shouldDeleteTrainByTrainKwr() {
        Integer trainKwr = 123;
        TrainEntity trainEntity = new TrainEntity();

        when(trainJpaRepository.findByTrainKwr(trainKwr)).thenReturn(Optional.of(trainEntity));

        trainRepository.delete(trainKwr);

        verify(trainJpaRepository, times(1)).delete(trainEntity);
    }

    @Test
    void shouldFindAll() {
        TrainEntity trainEntity = new TrainEntity();
        Train train = Train.builder().build();

        when(trainJpaRepository.findAll()).thenReturn(Collections.singletonList(trainEntity));
        when(trainMapper.mapFromEntity(trainEntity)).thenReturn(train);

        List<Train> result = trainRepository.findAll();

        assertEquals(1, result.size());
        assertEquals(train, result.get(0));
    }

    @Test
    void shouldSave() {
        TrainEntity trainEntity = TrainEntity.builder()
                .trainKwr(1)
                .trainNumber(123)
                .build();

        when(trainJpaRepository.save(any())).thenReturn(trainEntity);

        TrainEntity save = trainJpaRepository.save(trainEntity);

        verify(trainJpaRepository, times(1)).save(trainEntity);

        assertEquals(trainEntity, save);
    }
}
