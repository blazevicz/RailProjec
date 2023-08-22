package org.pl.deenes.infrastructure.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.TrainStatsEntity;
import org.pl.deenes.infrastructure.mapper.TrainStatsMapper;
import org.pl.deenes.infrastructure.repositories.jpa.TrainStatsJPaRepository;
import org.pl.deenes.model.TrainStats;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainStatsRepositoryTest {

    @Mock
    private TrainStatsJPaRepository trainStatsJpaRepository;

    @Mock
    private TrainStatsMapper trainStatsMapper;

    @InjectMocks
    private TrainStatsRepository trainStatsRepository;

    @Test
    void testFindAllByTrainStatsId() {
        Integer trainStatsId = 1;
        TrainStatsEntity trainStatsEntity = TrainStatsEntity.builder().trainStatsId(trainStatsId).build();
        TrainStats trainStats = TrainStats.builder().trainStatsId(trainStatsId).build();

        when(trainStatsJpaRepository.findById(trainStatsId)).thenReturn(Optional.of(trainStatsEntity));
        when(trainStatsMapper.mapFromEntity(trainStatsEntity)).thenReturn(trainStats);

        Optional<TrainStats> result = trainStatsRepository.findAllByTrainStatsId(trainStatsId);

        assertEquals(trainStats, result.get());
    }

    @Test
    void testFindAll() {
        TrainStatsEntity trainStatsEntity1 = new TrainStatsEntity();
        TrainStatsEntity trainStatsEntity2 = new TrainStatsEntity();
        TrainStats trainStats1 = TrainStats.builder().build();
        TrainStats trainStats2 = TrainStats.builder().build();

        when(trainStatsJpaRepository.findAll()).thenReturn(Arrays.asList(trainStatsEntity1, trainStatsEntity2));
        when(trainStatsMapper.mapFromEntity(trainStatsEntity1)).thenReturn(trainStats1);
        when(trainStatsMapper.mapFromEntity(trainStatsEntity2)).thenReturn(trainStats2);

        List<TrainStats> result = trainStatsRepository.findAll();

        assertEquals(2, result.size());
        assertEquals(trainStats1, result.get(0));
        assertEquals(trainStats2, result.get(1));
    }
}
