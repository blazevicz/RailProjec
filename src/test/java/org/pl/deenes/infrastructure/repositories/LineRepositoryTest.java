package org.pl.deenes.infrastructure.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.infrastructure.entity.LineDetailsEntity;
import org.pl.deenes.infrastructure.entity.LineEntity;
import org.pl.deenes.infrastructure.mapper.LineDetailsMapper;
import org.pl.deenes.infrastructure.mapper.LineMapper;
import org.pl.deenes.infrastructure.repositories.jpa.LineDetailsJpaRepository;
import org.pl.deenes.infrastructure.repositories.jpa.LineJpaRepository;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.LineDetails;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LineRepositoryTest {

    @Mock
    private LineDetailsMapper lineDetailsMapper;

    @Mock
    private LineJpaRepository lineJpaRepository;

    @Mock
    private LineMapper lineMapper;

    @Mock
    private LineDetailsJpaRepository lineDetailsJpaRepository;

    @InjectMocks
    private LineRepository lineRepository;

    @Test
    void saveAllShouldSaveAllLineDetailsToDB() {
        LineDetails lineDetails = new LineDetails();
        LineDetailsEntity lineDetailsEntity = new LineDetailsEntity();

        when(lineDetailsMapper.mapToEntity(lineDetails)).thenReturn(lineDetailsEntity);

        lineRepository.saveALl(Collections.singletonList(lineDetails));

        verify(lineDetailsMapper, times(1)).mapToEntity(any(LineDetails.class));
        verify(lineDetailsJpaRepository, times(1)).saveAll(anyList());
    }

    @Test
    void shouldFindLineByLineNumber() {
        Integer lineNumber = 123;
        LineEntity lineEntity = new LineEntity();
        Line expectedLine = new Line(1, List.of(1.1, 2.2));

        when(lineJpaRepository.findByLineNumber(lineNumber)).thenReturn(lineEntity);
        when(lineMapper.mapFromEntity(lineEntity)).thenReturn(expectedLine);

        Line resultLine = lineRepository.findLine(lineNumber);

        assertEquals(expectedLine, resultLine);

        verify(lineJpaRepository, times(1)).findByLineNumber(lineNumber);
        verify(lineMapper, times(1)).mapFromEntity(lineEntity);
    }
}
