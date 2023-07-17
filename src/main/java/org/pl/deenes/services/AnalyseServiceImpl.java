package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Line;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.TrainStats;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AnalyseServiceImpl implements AnalyseService {

    private TimetableImpl readKilometersServiceImpl;
    private TrainStatsServiceImpl trainStatsService;

    @Override
    public Analyse creatingTrainAnalyse(TrainStats trainStats) {
        String textForRegion = readKilometersServiceImpl.getTextToAnalyse();
        String grossTextToAnalyse = readKilometersServiceImpl.getBruttoTextToAnalyse();
        var string = Arrays.stream(grossTextToAnalyse.split("\\s")).toList();

        List<String> splitTrainDetailsForAnalyse = Arrays.stream(textForRegion.split("\\s")).toList();
        String[] split = textForRegion.trim().split("Relacja");
        String[] relationAtoB = split[1].split("-");

        return Analyse.builder()
                .locomotiveType(LocomotiveType.valueOf(string.get(11)))
                .trainMaxWeight(Integer.parseInt(string.get(12)))
                .trainMaxSpeed(Integer.parseInt(string.get(13)))
                .brakePercent(Integer.parseInt(string.get(14)))
                .trainMaxLength(Integer.parseInt(string.get(15)))
                .trainType(splitTrainDetailsForAnalyse.get(0))
                .trainNumber(Integer.parseInt(splitTrainDetailsForAnalyse.get(1).substring(0, splitTrainDetailsForAnalyse.get(1).length() - 2)))
                .trainKwr(Integer.parseInt(splitTrainDetailsForAnalyse.get(2).replace("(", "").replace(")", "")))
                .startStation(relationAtoB[0].trim())
                .endStation(relationAtoB[1].trim().replaceFirst(".$", "").trim())
                .trainStats(TrainStats.builder()
                        .lineList(trainStats.getLineList())
                        .lineWithFirstLastKm(trainStatsService.mapWithLineNumberAndFirstLastKilometr((LinkedList<Line>) trainStats.getLineList()))
                        .build())
                .build();
    }

}
