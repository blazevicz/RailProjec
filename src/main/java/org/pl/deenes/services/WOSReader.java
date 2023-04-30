package org.pl.deenes.services;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.pl.deenes.data.LineEntry;
import org.pl.deenes.repositories.LineEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class WOSReader {
    private final LineEntryRepository lineEntryRepository;
    private EntityManager entityManager;

    private final File source = Path.of("src/main/resources/IDDE4 Dodatek 2 IZ Sosnowiec z popr 6 od 21 IV 23.pdf").toFile();

    private static List<String> formatToOneLine(List<String> lines) {
        List<String> formattedLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty() && !line.matches("\\d+ .*")) {
                String previousLine = formattedLines.remove(formattedLines.size() - 1);
                formattedLines.add(previousLine + " " + line);
            } else {
                formattedLines.add(line);
            }
        }
        return formattedLines;
    }

    public void loadWosPDF() throws IOException {

        try (PDDocument loadPDF = PDDocument.load(source)) {
            PDFTextStripper stripper = new PDFTextStripper();

            //strony od 3 do 9
            stripper.setStartPage(4);
            stripper.setEndPage(4);
            String text = stripper.getText(loadPDF);
            List<String> lines = Arrays.stream(text.split("\n")).toList();
            List<String> strings = formatToOneLine(lines);
            String regex = "\\s+(?=[\\d-])|(?<=[\\d-])\\s+";
            List<String> collect = strings.stream().skip(2).toList();
            System.out.println(collect);


            for (String formattedLine : collect) {
                String[] parts = formattedLine.split(regex);
                List<String> collect1 = Arrays.stream(parts).filter(a -> !a.equals("-")).toList();

                LineEntry build = LineEntry.builder()
                        .lineNumber(Integer.valueOf(collect1.get(0)))
                        .startStation(collect1.get(1))
                        .endStation(collect1.get(2))
                        .startKilometer(Double.valueOf(collect1.get(3).replace(",", ".")))
                        .endKilometer(Double.valueOf(collect1.get(4).replace(",", ".")))
                        .page(Integer.valueOf(collect1.get(5)))
                        .railwayRegion(4)
                        .build();

                //  addLineEntry(build);
                lineEntryRepository.saveAndFlush(build);
                entityManager.persist(build);
                entityManager.flush();

                System.out.println(build);

            }
        }
    }

    public LineEntry addLineEntry(LineEntry lineEntry) {
        return lineEntryRepository.save(lineEntry);
    }
}
