package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.pl.deenes.data.Analyse;
import org.pl.deenes.data.RoadStats;
import org.pl.deenes.data.Train;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@ToString
@AllArgsConstructor
public class TrainService {
    private ReadKilometers readKilometers;
    private RoadStatsService roadStatsService;
    private CalculateKilometers calculateKilometers;
    private AnalyseService analyseService;

    private static LocalDate extractingDate(List<String> split) {
        Optional<String> day = split.stream().filter(a -> a.contains("dnia")).limit(1).findFirst();
        if (day.isPresent()) {
            String[] split1 = day.get().split(" ");
            String[] split2 = split1[split1.length - 1].split("\\.");
            return LocalDate.of(Integer.parseInt(split2[2]), Integer.parseInt(split2[1]), Integer.parseInt(split2[0]));
        }
        return null;
    }

    public Train trainCreate() {
        readKilometers.setFile(Files.myPatch());
        Kilometers reader = readKilometers.reader();

        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        calculateKilometers.setKilometers(reader);

        String companyName = readKilometers.getCompanyName();
        var split = Arrays.stream(companyName.split("\n")).toList();
        LocalDate localDate = extractingDate(split);

        RoadStats roadStats = roadStatsService.calculateKilometers(roadStatsService.getLastKilometer());
        Analyse analyse = analyseService.creatingTrainAnalyse();

        return Train.builder()
                .companyName(split.get(2))
                .datePlan(localDate)
                .roadStats(roadStats.getHowManyKilometers())
                .analyse(analyse)
                .lineList(roadStats.getLineList())
                .build();

    }
}
