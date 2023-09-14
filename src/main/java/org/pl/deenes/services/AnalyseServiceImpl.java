package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.LocomotiveType;
import org.pl.deenes.model.TrainStats;
import org.pl.deenes.services.interfaces.AnalyseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
@EqualsAndHashCode
public class AnalyseServiceImpl implements AnalyseService {

    private TimetableImpl readKilometersServiceImpl;
    private TrainStatsServiceImpl trainStatsService;

    private static Analyse buildAnalyse(List<String> string, List<String> splitTrainDetailsForAnalyse, String[] relationAtoB) {
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
                .build();
    }

    @Override
    public Analyse creatingTrainAnalyse(@NonNull TrainStats trainStats, List<String> stations) {
        Result relationFromText = getRelationFromText();

        Map<Integer, List<Double>> mapWithLineNumberAndFirstLastKilometer =
                trainStatsService.mapWithLineNumberAndFirstLastKilometer(trainStats.getLineList());
        List<TrainStats> trainStatsList = trainStatsCreator(mapWithLineNumberAndFirstLastKilometer, stations);

        Analyse analyse = buildAnalyse(relationFromText.string(), relationFromText.splitTrainDetailsForAnalyse(), relationFromText.relationAtoB());
        analyse.setTrainStats(new ArrayList<>(trainStatsList));
        trainStatsList.forEach(analyse::addStat);

        return analyse;
    }

    @NonNull
    private Result getRelationFromText() {
        String textForRegion = readKilometersServiceImpl.getTextToAnalyse();
        String grossTextToAnalyse = readKilometersServiceImpl.getBruttoTextToAnalyse();
        var string = Arrays.stream(grossTextToAnalyse.split("\\s")).toList();

        List<String> splitTrainDetailsForAnalyse = Arrays.stream(textForRegion.split("\\s")).toList();
        String[] split = textForRegion.trim().split("Relacja");
        String[] relationAtoB = split[1].split("-");
        return new Result(string, splitTrainDetailsForAnalyse, relationAtoB);
    }

    public List<TrainStats> trainStatsCreator(@NonNull Map<Integer, List<Double>> mapWithLineNumberAndFirstLastKilometer, List<String> stations) {
        List<TrainStats> trainStatsList = new LinkedList<>();

        for (Map.Entry<Integer, List<Double>> integerListEntry : mapWithLineNumberAndFirstLastKilometer.entrySet()) {
            TrainStats build = TrainStats.builder()
                    .lineNumber(integerListEntry.getKey())
                    .firstKilometer(integerListEntry.getValue().get(0))
                    .lastKilometer(integerListEntry.getValue().get(integerListEntry.getValue().size() - 1))
                    .build();
            trainStatsList.add(build);
        }
        for (int i = 0; i < stations.size(); i++) {
            TrainStats trainStats = trainStatsList.get(i);
            trainStats.setStation(stations.get(i));
        }
        return trainStatsList;
    }

    private record Result(List<String> string, List<String> splitTrainDetailsForAnalyse, String[] relationAtoB) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return Objects.equals(string, result.string) && Objects.equals(splitTrainDetailsForAnalyse, result.splitTrainDetailsForAnalyse) && Arrays.equals(relationAtoB, result.relationAtoB);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(string, splitTrainDetailsForAnalyse);
            result = 31 * result + Arrays.hashCode(relationAtoB);
            return result;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "string=" + string +
                    ", splitTrainDetailsForAnalyse=" + splitTrainDetailsForAnalyse +
                    ", relationAtoB=" + Arrays.toString(relationAtoB) +
                    '}';
        }
    }
}

