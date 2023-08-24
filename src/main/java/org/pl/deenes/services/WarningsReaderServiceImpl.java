package org.pl.deenes.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.pl.deenes.api.controller.exception.NotFound;
import org.pl.deenes.infrastructure.entity.CautionEntity;
import org.pl.deenes.infrastructure.repositories.CautionRepository;
import org.pl.deenes.model.Caution;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class WarningsReaderServiceImpl {

    private static final String PDF_LINK = "src/main/resources/IDDE4 Dodatek 2 IZ Sosnowiec z popr 6 od 21 IV 23.pdf";
    private static final int TITLE_PAGE_TO_SKIP = 1;
    private static final String LINE_WITH_PAGE_NUMBER_TO_SKIP = "1 2 3 4 5 6 7 8 9";
    private static final String REGEX_FOR_SPLIT_LINES = "    |   ";

    private final CautionRepository cautionRepository;

    private static List<String> prepareStringToGetLines(@NonNull String string) {
        return Arrays.stream(string.replaceAll("\\s", " ")
                        .replace(LINE_WITH_PAGE_NUMBER_TO_SKIP, "")
                        .split(REGEX_FOR_SPLIT_LINES))
                .filter(line -> line.length() >= 4)
                .toList();
    }

    @NonNull
    private static PDFTextStripper settingsForRead() throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setStartPage(11);
        stripper.setEndPage(85);
        return stripper;
    }

    String prepareBeforeCreatingObjects(@NonNull String lineToPrepare) {
        if (lineToPrepare.isEmpty()) {
            throw new NotFound("no valid line");
        }
        var splitBySpace = new java.util.ArrayList<>(Arrays.stream(lineToPrepare.split(" ")).toList());
        try {
            String replace = splitBySpace.get(0).replace(",", ".");
            Double.parseDouble(replace);
        } catch (NumberFormatException e) {
            splitBySpace.remove(0);
            return prepareBeforeCreatingObjects(String.join(" ", splitBySpace).trim());
        }
        return String.join(" ", splitBySpace).trim();
    }

    public List<CautionEntity> loadWarningsFromPDF() {
        List<Caution> cautionsList = new ArrayList<>();

        try (PDDocument loadPDF = PDDocument.load(Path.of(PDF_LINK).toFile())) {

            PDFTextStripper stripper = settingsForRead();
            String text = stripper.getText(loadPDF);

            String[] splitByLineNumber = text.split("Linia nr ");
            List<String> list = Arrays.stream(splitByLineNumber).skip(TITLE_PAGE_TO_SKIP).toList();

            for (String string : list) {
                var lineWithCautionsLineByLine = prepareStringToGetLines(string);
                for (String oneLineWithCaution : lineWithCautionsLineByLine) {
                    char charWithLineNumber = lineWithCautionsLineByLine.get(0).charAt(0);
                    Integer lineNumber = (int) charWithLineNumber;
                    cautionCreator(oneLineWithCaution, lineNumber, cautionsList);
                }
            }
        } catch (IOException e) {
            log.warn("cant read pdf " + e.getMessage());
        }
        cautionsList.removeIf(Objects::isNull);

        return cautionRepository.saveAll(cautionsList);
    }

    private void cautionCreator(String oneLineWithCaution, Integer lineNumber, List<Caution> cautionsList) {
        try {
            creatingCautionAndAddToList(oneLineWithCaution, lineNumber, cautionsList);
        } catch (NotFound notFound) {
            log.warn("Problem with caution: Line: (%s), line number: (%s) ".formatted(oneLineWithCaution, lineNumber) + notFound.getMessage());
        }
    }

    private void creatingCautionAndAddToList(String s1, Integer lineNumber, List<Caution> cautionsList) {
        if (s1 != null && lineNumber != null && cautionsList != null) {
            String s = prepareBeforeCreatingObjects(s1);
            Caution caution = convertStringToCaution(s, lineNumber);
            cautionsList.add(caution);
        } else {
            log.warn("Caution jest nullem z linii %s".formatted(s1));
        }
    }

    Caution convertStringToCaution(@NonNull String line, Integer lineNumber) {
        String[] s1 = line.split(" ");
        try {
            return Caution.builder()
                    .lineNumber(lineNumber)
                    .startFrom(Double.valueOf(s1[0].replace(",", ".")))
                    .endOn(checkDoesEndKilometerExist(s1))
                    .reason(prepareIfReasonExist(s1[2], line))
                    .trackNumber(s1[3])
                    .leftTrack((s1[4]))
                    .rightTrack(s1[5])
                    .comments(String.join(" ", Arrays.copyOfRange(s1, 6, s1.length)).trim())
                    .build();
        } catch (NumberFormatException nfe) {
            log.warn("error while preparing Caution from line: " + line);
        } catch (ArrayIndexOutOfBoundsException arr) {
            log.warn(arr.getMessage() + "while reading %s".formatted(Arrays.toString(s1)));
        }
        return null;
    }

    private Double checkDoesEndKilometerExist(String @NonNull [] splitLine) {
        if (splitLine.length >= 2) {
            String replace = splitLine[1].replace(",", ".");
            try {
                return Double.parseDouble(replace);
            } catch (NumberFormatException numberFormatException) {
                return Double.valueOf(splitLine[0].replace(",", "."));
            }
        } else {
            throw new NotFound("arr dont have index 1:" + Arrays.toString(splitLine));
        }
    }

    private String prepareIfReasonExist(@NonNull String reason, String line) {
        if (!reason.equals("-")) {
            List<String> list = new java.util.ArrayList<>(Arrays.stream(line.split(" ")).toList());
            list.remove(0);
            list.remove(0);
        }
        return reason;
    }
}
