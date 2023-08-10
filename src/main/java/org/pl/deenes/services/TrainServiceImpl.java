package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.infrastructure.repositories.LineRepository;
import org.pl.deenes.infrastructure.repositories.dao.AnalyseDAO;
import org.pl.deenes.infrastructure.repositories.dao.TrainDAO;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Train;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.interfaces.AnalyseService;
import org.pl.deenes.services.interfaces.TrainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@ToString
@AllArgsConstructor
@Slf4j
@Transactional
public class TrainServiceImpl implements TrainService {

    private final TimetableImpl readKilometersServiceImpl;
    private final TrainStatsServiceImpl trainStatsServiceImpl;
    private final CalculateKilometersServiceImpl calculateKilometers;
    private final AnalyseService analyseServiceImpl;
    private final TrainDAO trainDAO;
    private final AnalyseDAO analyseDAO;
    private final LineRepository lineRepository;

    private static Optional<LocalDate> extractingDate(@NonNull List<String> split) {
        Optional<String> day = split.stream().filter(a -> a.contains("dnia")).limit(1).findFirst();
        if (day.isPresent()) {
            String[] split1 = day.get().split(" ");
            String[] split2 = split1[split1.length - 1].split("\\.");
            return Optional.of(LocalDate.of(Integer.parseInt(split2[2]), Integer.parseInt(split2[1]), Integer.parseInt(split2[0])));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Train trainCreate(String link) {
        readKilometersServiceImpl.setFile(new File(link));
        TimetableDetails reader = readKilometersServiceImpl.read(link);

        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        calculateKilometers.setTimetableDetails(reader);
        String companyName = readKilometersServiceImpl.getCompanyName();
        var split = Arrays.stream(companyName.split("\n")).toList();
        LocalDate localDate = extractingDate(split).orElseThrow();

        TrainStats trainStats = trainStatsServiceImpl.calculateKilometers(trainStatsServiceImpl.getLastKilometer());
        Analyse analyse = analyseServiceImpl.creatingTrainAnalyse(trainStats);


        Train train = Train.builder()
                .companyName(split.get(2))
                .trainNumber(analyse.getTrainNumber())
                .brakePercent(analyse.getBrakePercent())
                .trainType(analyse.getTrainType())
                .startStation(analyse.getStartStation())
                .endStation(analyse.getEndStation())
                .trainMaxLength(analyse.getTrainMaxLength())
                .trainMaxWeight(analyse.getTrainMaxWeight())
                .locomotiveType(analyse.getLocomotiveType())
                .trainMaxSpeed(analyse.getTrainMaxSpeed())
                .trainStats(analyse.getTrainStats())
                .trainKwr(analyse.getTrainKwr())
                .datePlan(localDate)
                .roadStats(trainStats.getHowManyKilometers())
                .trainStats(List.of(analyse.getTrainStats().get(0)))
                .build();
        // train.setTrainStats(analyse.getTrainStats());


        for (TrainStats stat : train.getTrainStats()) {
            stat.setTrain(train);
        }
        return train;
    }

    @Transactional
    public Train saveTrain(@NonNull Train train) {
        log.warn(train.toString());
        return trainDAO.save(train);
    }

    @Transactional
    public Train findTrain(Integer kwr) {
        Optional<Train> train = trainDAO.find(kwr);
        return train.orElseThrow();

    }
}
