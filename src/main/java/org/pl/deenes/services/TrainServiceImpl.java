package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.model.*;
import org.pl.deenes.services.dao.AnalyseDAO;
import org.pl.deenes.services.dao.TrainDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@ToString
@AllArgsConstructor
@Slf4j
public class TrainServiceImpl implements TrainService {

    private final TimetableImpl readKilometersServiceImpl;
    private final TrainStatsServiceImpl trainStatsServiceImpl;
    private final CalculateKilometersServiceImpl calculateKilometers;
    private final AnalyseService analyseServiceImpl;
    private final TrainDAO trainDAO;
    private final AnalyseDAO analyseDAO;

    private static LocalDate extractingDate(List<String> split) {
        Optional<String> day = split.stream().filter(a -> a.contains("dnia")).limit(1).findFirst();
        if (day.isPresent()) {
            String[] split1 = day.get().split(" ");
            String[] split2 = split1[split1.length - 1].split("\\.");
            return LocalDate.of(Integer.parseInt(split2[2]), Integer.parseInt(split2[1]), Integer.parseInt(split2[0]));
        }
        return null;
    }


    @Transactional
    public Optional<Train> findTrain(Integer trainKwr) {
        return trainDAO.find(trainKwr);

    }

    @Override
    @Transactional
    public Train trainCreate() {
        readKilometersServiceImpl.setFile(Files.myPatch());
        TimetableDetails reader = readKilometersServiceImpl.read();

        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        calculateKilometers.setTimetableDetails(reader);
        String companyName = readKilometersServiceImpl.getCompanyName();
        var split = Arrays.stream(companyName.split("\n")).toList();
        LocalDate localDate = extractingDate(split);

        TrainStats trainStats = trainStatsServiceImpl.calculateKilometers(trainStatsServiceImpl.getLastKilometer());
        Analyse analyse = analyseServiceImpl.creatingTrainAnalyse(trainStats);
        Set<Line> collect = new HashSet<>(trainStats.getLineList());

        Map<Integer, List<Double>> integerListMap = trainStatsServiceImpl.mapWithLineNumberAndFirstLastKilometr((LinkedList<Line>) trainStats.getLineList());

        return Train.builder()
                .companyName(split.get(2))
                .trainKwr(analyse.getTrainKwr())
                .datePlan(localDate)
                .roadStats(trainStats.getHowManyKilometers())
                .analyse(analyse)
                .line(collect)
                //.lineList(trainStats.getLineList())
                .build();

    }
}
