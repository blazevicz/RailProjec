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
public class TrainServiceImpl implements TrainService {
    private ReadKilometersServiceImpl readKilometersServiceImpl;
    private RoadStatsServiceImpl roadStatsServiceImpl;
    private CalculateKilometersServiceImpl calculateKilometers;
    private AnalyseService analyseServiceImpl;

    private static LocalDate extractingDate(List<String> split) {
        Optional<String> day = split.stream().filter(a -> a.contains("dnia")).limit(1).findFirst();
        if (day.isPresent()) {
            String[] split1 = day.get().split(" ");
            String[] split2 = split1[split1.length - 1].split("\\.");
            return LocalDate.of(Integer.parseInt(split2[2]), Integer.parseInt(split2[1]), Integer.parseInt(split2[0]));
        }
        return null;
    }

    @Override
    public Train trainCreate() {
        readKilometersServiceImpl.setFile(Files.myPatch());
        KilometersServiceImpl reader = readKilometersServiceImpl.reader();

        reader.getAllRailwayLines();
        reader.giveAllKilometers();
        calculateKilometers.setKilometersServiceImpl(reader);
        String companyName = readKilometersServiceImpl.getCompanyName();
        var split = Arrays.stream(companyName.split("\n")).toList();
        LocalDate localDate = extractingDate(split);

        RoadStats roadStats = roadStatsServiceImpl.calculateKilometers(roadStatsServiceImpl.getLastKilometer());
        Analyse analyse = analyseServiceImpl.creatingTrainAnalyse();

        return Train.builder()
                .companyName(split.get(2))
                .datePlan(localDate)
                .roadStats(roadStats.getHowManyKilometers())
                .analyse(analyse)
                .lineList(roadStats.getLineList())
                .build();

    }
}
