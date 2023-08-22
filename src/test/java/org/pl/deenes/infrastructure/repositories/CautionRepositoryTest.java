package org.pl.deenes.infrastructure.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pl.deenes.infrastructure.entity.CautionEntity;
import org.pl.deenes.infrastructure.mapper.CautionMapper;
import org.pl.deenes.infrastructure.repositories.dao.CautionDAO;
import org.pl.deenes.infrastructure.repositories.jpa.CautionJpaRepository;
import org.pl.deenes.model.Caution;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

class CautionRepositoryTest {

    @Mock
    private CautionMapper cautionMapper;

    @Mock
    private CautionJpaRepository cautionJpaRepository;

    private CautionDAO cautionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cautionRepository = new CautionRepository(cautionMapper, cautionJpaRepository);
    }

    @Test
    void testSaveAll() {
        // Arrange
        List<Caution> cautions = List.of(Caution.builder().build(), Caution.builder().build());
        List<CautionEntity> entities = cautions.stream()
                .map(caution -> {
                    CautionEntity entity = new CautionEntity();
                    // Set properties on entity using caution
                    return entity;
                })
                .collect(Collectors.toList());

        when(cautionMapper.mapToEntity(any(Caution.class))).thenAnswer(invocation -> {
            Caution caution = invocation.getArgument(0);
            return new CautionEntity();
        });

        when(cautionJpaRepository.saveAll(anyList())).thenReturn(entities);

        // Act
        List<CautionEntity> savedEntities = cautionRepository.saveAll(cautions);

        Assertions.assertEquals(2, savedEntities.size());
    }
}
