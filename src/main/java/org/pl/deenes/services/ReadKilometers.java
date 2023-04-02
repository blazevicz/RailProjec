package org.pl.deenes.services;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.pl.deenes.data.Positions;
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
            stripper.getTextForRegion(Positions.LEFT.name());
            stripper.getTextForRegion(Positions.RIGHT.name());
            addingToList(stripper, Positions.LEFT.name(), kilometers);
            addingToList(stripper, Positions.RIGHT.name(), kilometers);

            if (currentPage == pages.getCount() - 1) {
                int lastPageNumber = pages.getCount();
                stripper.setStartPage(lastPageNumber);
                stripper.setEndPage(lastPageNumber);
                String text = stripper.getTextForRegion(Positions.TITLE.name());
                gettingLastKilometer(text);
/*
                System.out.println("TESTTTTTT");
                System.out.println(lastPageNumber);
                System.out.println(pages.getCount());
                System.out.println(text);
                System.out.println("TESTTTTTT");*/

            }
            currentPage++;
        }
        loadPDF.close();
        return kilometers;
    }

    private void gettingLastKilometer(String text) {
        String[] split = text.split("\\s");
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

        log.info("test" + listaDouble);
    }


    private void addingToList(PDFTextStripperByArea stripper, String leftOrRight, Kilometers kilometers) {
        String[] kilometerColumnArr = getKilometerColumn(stripper, leftOrRight);
        log.info(Arrays.stream(kilometerColumnArr).toList().toString());
        kilometers.getAllKilometers().add(List.of(kilometerColumnArr));
    }

    private String[] getKilometerColumn(PDFTextStripperByArea stripper, String leftOrRight) {
        return stripper.getTextForRegion(leftOrRight).split("\\s");
    }

    private void addRegionsConfiguration(PDFTextStripperByArea stripper) {
        stripper.addRegion(Positions.TITLE.name(), new Rectangle(12, 20, 389, 553));
        stripper.addRegion(Positions.LEFT.name(), new Rectangle(31, 45, 57, 518));
        stripper.addRegion(Positions.RIGHT.name(), new Rectangle(449, 45, 57, 518));
    }
}
