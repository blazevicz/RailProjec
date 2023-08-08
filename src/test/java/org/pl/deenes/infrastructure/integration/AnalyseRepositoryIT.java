package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pl.deenes.configuration.MethodNotImplementedException;
import org.pl.deenes.infrastructure.repositories.AnalyseRepository;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.LocomotiveType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class AnalyseRepositoryIT extends IntegrationReposIT {

    private AnalyseRepository analyseRepository;

    @Test
    void shouldThrowMethodNotImplementedException() {
        int trainKwr = 123;
        assertThrows(MethodNotImplementedException.class, () -> analyseRepository.find(trainKwr));
    }

    @Test
    void shouldFindAllAnalysisByTrainKwr() {
        Analyse analyse1 = Analyse.builder()
                .locomotiveType(LocomotiveType.ET22)
                .trainNumber(1)
                .trainMaxLength(1)
                .trainMaxSpeed(1)
                .trainMaxWeight(2)
                .trainType("ABC")
                .startStation("A")
                .endStation("B")
                .trainKwr(123)
                .build();
        Analyse analyse2 = Analyse.builder()
                .locomotiveType(LocomotiveType.ET22)
                .trainNumber(1)
                .trainMaxLength(1)
                .trainMaxSpeed(1)
                .trainMaxWeight(2)
                .trainType("ABC")
                .startStation("A")
                .endStation("B")
                .trainKwr(123)
                .build();

        analyseRepository.save(analyse1);
        analyseRepository.save(analyse2);

        List<Analyse> allByTrainKwr = analyseRepository.findAllByTrainKwr(123);
        assertEquals(2, allByTrainKwr.size());

    }

    @Test
    void shouldSaveAnalyse() {
        Analyse analyse = Analyse.builder()
                .locomotiveType(LocomotiveType.ET22)
                .trainNumber(1)
                .trainMaxLength(1)
                .trainMaxSpeed(1)
                .trainMaxWeight(2)
                .trainType("ABC")
                .startStation("A")
                .endStation("B")
                .trainKwr(123)
                .build();

        Analyse savedAnalyse = analyseRepository.save(analyse);
        List<Analyse> allByTrainKwr = analyseRepository.findAllByTrainKwr(123);

        assertEquals(analyse.getTrainKwr(), savedAnalyse.getTrainKwr());
        assertEquals(analyse, allByTrainKwr.get(0));
    }
}

