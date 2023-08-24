package org.pl.deenes.infrastructure.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.CautionEntity;
import org.pl.deenes.infrastructure.mapper.CautionMapper;
import org.pl.deenes.infrastructure.repositories.jpa.CautionJpaRepository;
import org.pl.deenes.model.Caution;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CautionRepositoryTest {
    @Mock
    private CautionMapper cautionMapper;

    @Mock
    private CautionJpaRepository cautionJpaRepository;

    @InjectMocks
    private CautionRepository cautionRepository;

    @Test
    void testSaveAll() {
        List<Caution> cautions = List.of(Caution.builder().build(), Caution.builder().build());
        List<CautionEntity> entities = cautions.stream()
                .map(caution -> {
                    return new CautionEntity();
                })
                .collect(Collectors.toList());

        when(cautionMapper.mapToEntity(any(Caution.class))).thenAnswer(invocation -> {
            return new CautionEntity();
        });
        when(cautionJpaRepository.saveAll(anyList())).thenReturn(entities);

        List<CautionEntity> savedEntities = cautionRepository.saveAll(cautions);

        Assertions.assertEquals(2, savedEntities.size());
    }
}
