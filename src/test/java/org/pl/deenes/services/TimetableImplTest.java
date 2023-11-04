package org.pl.deenes.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
class TimetableImplTest {
    @InjectMocks
    TimetableImpl timetable;
    @Mock
    private TrainStatsServiceImpl trainStatsService;

    @Test
    void testReadLoadingPdfException() {
        try (MockedStatic<PDDocument> pdDocumentMockedStatic = mockStatic(PDDocument.class)) {
            pdDocumentMockedStatic.when(()
                    -> PDDocument.load(Path.of(anyString()).toFile())).thenReturn(new PDDocument());
        }
        assertThrows(FileNotFoundException.class, () -> timetable.read("path/to/pdf"));
    }
}