package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.pl.deenes.data.LineEntry;
import org.pl.deenes.data.ZLK;
import org.pl.deenes.repositories.LineEntryRepository;
import org.pl.deenes.repositories.ZLKRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class WOSReader {
    private final File source = Path.of("src/main/resources/IDDE4 Dodatek 2 IZ Sosnowiec z popr 6 od 21 IV 23.pdf").toFile();
    private LineEntryRepository lineEntryRepository;
    private ZLKRepo zlkRepo;

    private static void creatingLineEntrierAndAddToList(String regex, List<String> collect, List<LineEntry> lineEntriesToSave) {
        for (String formattedLine : collect) {
            String[] parts = formattedLine.split(regex);
            List<String> collect1 = Arrays.stream(parts).filter(a -> !a.equals("-")).toList();
            LineEntry buildLineEntry = null;
            try {
                buildLineEntry = buildingLineEntryObject(collect1);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                log.warn(e.getMessage());
            }
            if (buildLineEntry != null) {
                lineEntriesToSave.add(buildLineEntry);
            }
        }
    }

    private static LineEntry buildingLineEntryObject(List<String> collect1) {
        LineEntry buildLineEntry;
        buildLineEntry = LineEntry.builder()
                .lineNumber(Integer.valueOf(collect1.get(0)))
                .startStation(collect1.get(1))
                .endStation(collect1.get(2))
                .startKilometer(Double.valueOf(collect1.get(3).replace(",", ".")))
                .endKilometer(Double.valueOf(collect1.get(4).replace(",", ".")))
                .page(Integer.valueOf(collect1.get(5)))
                .railwayRegion(4)
                .build();
        return buildLineEntry;
    }

    private static List<String> formatToOneLine(List<String> lines) {
        List<String> formattedLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty() && !line.matches("\\d+ .*")) {
                if (formattedLines.size() - 1 < 0) {
                    continue;
                }
                String previousLine = formattedLines.remove(formattedLines.size() - 1);
                formattedLines.add(previousLine + " " + line);
            } else {
                formattedLines.add(line);
            }
        }
        return formattedLines;
    }

    @Transactional
    public void loadWosPDF() throws IOException {

        List<ZLK> all = zlkRepo.findAll();
        String actualWOSlink = all.get(0).getActualWOSlink();
        URL pdfUrl = new URL(
                "https://drive.google.com/u/0/uc?id=1r_YqFDkm-FL2ES_qrwW2zY-8OZX4dM3Z&export=download");


        try (PDDocument loadPDF = PDDocument.load(pdfUrl.openStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(3);
            stripper.setEndPage(9);

            String text = stripper.getText(loadPDF);
            List<String> lines = Arrays.stream(text.split("\n")).toList();
            List<String> strings = formatToOneLine(lines);
            String regex = "\\s+(?=[\\d-])|(?<=[\\d-])\\s+";
            List<String> collect = strings.stream().skip(2).toList();
            //temporary cleaning db
            //   lineEntryRepository.deleteAll();
            List<LineEntry> lineEntriesToSave = new ArrayList<>();
            creatingLineEntrierAndAddToList(regex, collect, lineEntriesToSave);
            lineEntryRepository.saveAll(lineEntriesToSave);
        }
    }

    public void loadRailwayProfileFromPDF() throws IOException {


        try (PDDocument loadPDF = PDDocument.load(new File("src/main/resources/IDDE4 Dodatek 1 IZ Sosnowiec z popr 14.pdf"))) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(256);
            stripper.setEndPage(275);
            log.warn("TEST");
            String text = stripper.getText(loadPDF);
            int length = text.split("\n").length;
            var collect =
                    Arrays.stream(text.split("\n"))
                            .skip(76)
                            .filter(a -> !a.contains("1 2 3 4 5 6 7 8 9 10") && a.length() > 2)
                            .filter(str -> !(str.trim().matches("\\d{3}") && str.trim().length() == 3))
                            .collect(Collectors.toList());


            Map<String, List<String>> map = collect.stream()
                    .filter(line -> line.contains("Linia nr"))
                    .collect(Collectors.toMap(
                            String::trim,
                            line -> {
                                int startIndex = collect.indexOf(line) + 1;
                                int endIndex = collect.subList(startIndex, collect.size()).stream()
                                        .filter(subline -> subline.contains("Linia nr"))
                                        .findFirst()
                                        .map(collect::indexOf)
                                        .orElse(collect.size());
                                return collect.subList(startIndex, endIndex);
                            }
                    ));

            map.entrySet().forEach(a -> System.out.println(a.getKey() + " " + a.getValue()));
            //map.entrySet().forEach(a-> System.out.println(a.getValue().size()));


        }
    }
}
