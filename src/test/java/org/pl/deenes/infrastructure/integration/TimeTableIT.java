package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pl.deenes.services.TimetableDetails;
import org.pl.deenes.services.TimetableImpl;
import org.pl.deenes.services.TrainStatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class TimeTableIT extends IntegrationReposIT {

    @MockBean
    private TrainStatsServiceImpl trainStatsService;

    @Test
    void shouldReadTimeTableFromPDF() {
        TimetableImpl timetableService = new TimetableImpl(trainStatsService);

        when(trainStatsService.calculateKilometers(any())).thenReturn(null);

        String testPdfFilePath = "src/test/resources/RJ_SKRJ_666401_464028_9.pdf";
        TimetableDetails timetableDetails = timetableService.read(testPdfFilePath);

        Assertions.assertNotNull(timetableDetails.getStations());
        Assertions.assertNotNull(timetableDetails.getAllKilometers());
        Assertions.assertNotNull(timetableDetails.getLineNumbers());
        Assertions.assertNotNull(timetableDetails.getKilometersAfterConvert());

    }
}
