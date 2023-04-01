package org.pl.deenes.services;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripperByArea;
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
@RequiredArgsConstructor
public class ReadKilometers {
    private static final String RIGHT = "right";
    private static final String LEFT = "left";
    private static final String TITLE = "title";
    private static final String LASTPAGE = "lastPage";
    private File file;


    public Kilometers reader() throws IOException {
        Kilometers kilometers = new Kilometers();
        PDDocument loadPDF = PDDocument.load(Files.myPatch());
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);

        addRegionsConfiguration(stripper);

        PDPageTree pages = loadPDF.getDocumentCatalog().getPages();
        int currentPage = 0;

        for (PDPage page : pages) {
            stripper.extractRegions(page);
            stripper.getTextForRegion(LEFT);
            stripper.getTextForRegion(RIGHT);
            addingToList(stripper, LEFT, kilometers);
            addingToList(stripper, RIGHT, kilometers);
            currentPage++;
        }
        loadPDF.close();
        return kilometers;
    }


    private void addingToList(PDFTextStripperByArea stripper, String leftOrRight, Kilometers kilometers) {
        String[] kilometerColumnArr = getKilometerColumn(stripper, leftOrRight);
        log.info(Arrays.stream(kilometerColumnArr).toList().toString());
        kilometers.getAllKilometers().add(List.of(kilometerColumnArr));
    }

    private void addingLastKilometer(PDFTextStripperByArea stripper, Kilometers kilometers) {
        String[] split = stripper.getTextForRegion(LASTPAGE).split("\\s");
        List<String> decimalNumList = new ArrayList<>();


        for (String s : split) {
            Pattern decimalNumPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            Matcher matcher = decimalNumPattern.matcher(s);

            while (matcher.find()) {
                decimalNumList.add(matcher.group());
            }
        }

        List<Double> listaDouble = decimalNumList.stream()
                .filter(s -> s != null && s.matches("^\\d+\\.\\d+$"))
                .map(Double::parseDouble).toList();
        RoadStatsService.lastKilometer = listaDouble.get(listaDouble.size() - 1);

        log.info(Arrays.toString(split));
    }


    private String[] getKilometerColumn(PDFTextStripperByArea stripper, String leftOrRight) {
        return stripper.getTextForRegion(leftOrRight).split("\\s");
    }

    private void addRegionsConfiguration(PDFTextStripperByArea stripper) {
        stripper.addRegion(TITLE, new Rectangle(12, 20, 389, 553));
        stripper.addRegion(LEFT, new Rectangle(31, 45, 57, 518));
        stripper.addRegion(RIGHT, new Rectangle(449, 45, 57, 518));
    }
}
