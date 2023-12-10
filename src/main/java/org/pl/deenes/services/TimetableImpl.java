package org.pl.deenes.services;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.pl.deenes.expections.LoadingPdfException;
import org.pl.deenes.model.Positions;
import org.pl.deenes.services.interfaces.Timetable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@Setter
@Getter
@RequiredArgsConstructor
public class TimetableImpl implements Timetable {

    private final TrainStatsServiceImpl trainStatsServiceImpl;
    private File file;
    private String textToAnalyse;
    private String bruttoTextToAnalyse;
    private String companyName;

    @Override
    public TimetableDetails read(String link) throws IOException {
        TimetableDetails timetableDetails = new TimetableDetails();
        File file1 = new ClassPathResource(link).getFile();

        try (PDDocument loadPDF = PDDocument.load(file1)) {

            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            addRegionsConfiguration(stripper);

            PDPageTree pages = loadPDF.getDocumentCatalog().getPages();
            int currentPage = 0;

            for (PDPage page : pages) {
                stripper.extractRegions(page);
                if (currentPage == 0) {
                    textToAnalyse = stripper.getTextForRegion(Positions.ANALYSIS.name());
                    bruttoTextToAnalyse = stripper.getTextForRegion(Positions.BRUTTO_ANALYSIS.name());
                    companyName = stripper.getTextForRegion(Positions.COMPANY_NAME.name());
                }
                String textForRegion1 = stripper.getTextForRegion(Positions.LEFT_STATIONS.name());
                var stationNames1 = searchStationNames(textForRegion1);

                String textForRegion = stripper.getTextForRegion(Positions.RIGHT_STATIONS.name());
                var stationNames = searchStationNames(textForRegion);

                timetableDetails.getStations().addAll(stationNames);
                timetableDetails.getStations().addAll(stationNames1);

                stripper.getTextForRegion(Positions.LEFT.name());
                stripper.getTextForRegion(Positions.RIGHT.name());
                addingToList(stripper, Positions.LEFT.name(), timetableDetails);
                addingToList(stripper, Positions.RIGHT.name(), timetableDetails);

                readingTitleFromFirstPage(currentPage, pages, stripper);
                currentPage++;
            }
        } catch (IOException e) {
            log.error("pdf not loading", e);
            throw new LoadingPdfException(e);
        }
        return timetableDetails;
    }

    private void readingTitleFromFirstPage(int currentPage, @NonNull PDPageTree pages, PDFTextStripperByArea stripper) {
        if (currentPage == pages.getCount() - 1) {
            int lastPageNumber = pages.getCount();
            stripper.setStartPage(lastPageNumber);
            stripper.setEndPage(lastPageNumber);
            String text = stripper.getTextForRegion(Positions.TITLE.name());
            gettingLastKilometer(text);
        }
    }

    public void gettingLastKilometer(@NonNull String text) {
        String[] split = text.split("\\s");
        List<String> decimalNumList = new ArrayList<>();
        for (String s : split) {
            Pattern decimalNumPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            Matcher matcher = decimalNumPattern.matcher(s);

            while (matcher.find()) {
                decimalNumList.add(matcher.group());
            }
        }

        List<Double> listDouble = decimalNumList.stream()
                .filter(s -> s != null && s.matches("^\\d+\\.\\d+$"))
                .map(Double::parseDouble).toList();

        trainStatsServiceImpl.setLastKilometer(listDouble.get(listDouble.size() - 1));
    }


    private void addingToList(PDFTextStripperByArea stripper, String leftOrRight, @NonNull TimetableDetails timetableDetails) {
        List<String> kilometersFromColumn = getKilometerColumn(stripper, leftOrRight);
        timetableDetails.getAllKilometers().add(kilometersFromColumn);
    }

    private List<String> getKilometerColumn(@NonNull PDFTextStripperByArea stripper, String leftOrRight) {
        return Arrays.stream(stripper.getTextForRegion(leftOrRight).split("\\s")).toList();
    }

    private void addRegionsConfiguration(@NonNull PDFTextStripperByArea stripper) {
        stripper.addRegion(Positions.COMPANY_NAME.name(), new Rectangle(59, 125, 302, 302));
        stripper.addRegion(Positions.BRUTTO_ANALYSIS.name(), new Rectangle(730, 41, 91, 548));
        stripper.addRegion(Positions.ANALYSIS.name(), new Rectangle(444, 25, 376, 18));
        stripper.addRegion(Positions.TITLE.name(), new Rectangle(12, 20, 389, 553));
        stripper.addRegion(Positions.LEFT.name(), new Rectangle(31, 45, 57, 518));
        stripper.addRegion(Positions.RIGHT.name(), new Rectangle(449, 45, 57, 518));
        stripper.addRegion(Positions.LEFT_STATIONS.name(), new Rectangle(127, 46, 123, 520));
        stripper.addRegion(Positions.RIGHT_STATIONS.name(), new Rectangle(546, 46, 123, 520));
    }

    private List<String> searchStationNames(@NonNull String textForRegion) {
        List<String> list = Arrays.stream(textForRegion.split("\\n")).toList();

        return list.stream()
                .filter(str -> str.length() >= 4)
                .filter(str -> Character.isLetter(str.charAt(0)) && !str.equals("Stacja"))
                .map(str -> str.replaceAll("\\b[a-z]\\w*\\s?", ""))
                .filter(str -> !str.isEmpty() && str.length() >= 4 && !Character.isLowerCase(str.charAt(0)))
                .map(String::trim)
                .toList();
    }
}