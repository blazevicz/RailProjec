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
import org.pl.deenes.configuration.LoadingPdfException;
import org.pl.deenes.data.Files;
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
@Getter
@RequiredArgsConstructor
public class ReadKilometersServiceImpl implements ReadKilometersService {

    private final RoadStatsServiceImpl roadStatsServiceImpl;
    private File file;
    private String textToAnalyse;
    private String bruttoTextToAnalyse;
    private String companyName;

    @Override
    public KilometersServiceImpl reader() {
        KilometersServiceImpl kilometersServiceImpl = new KilometersServiceImpl();

        try (PDDocument loadPDF = PDDocument.load(Files.myPatch())) {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            addRegionsConfiguration(stripper);

            PDPageTree pages = loadPDF.getDocumentCatalog().getPages();
            int currentPage = 0;

            for (PDPage page : pages) {
                stripper.extractRegions(page);
                if (currentPage == 0) {
                    textToAnalyse = stripper.getTextForRegion(Positions.ANALYSIS.name());
                    bruttoTextToAnalyse = stripper.getTextForRegion(Positions.BRUTTOANALYSIS.name());
                    companyName = stripper.getTextForRegion(Positions.COMPANYNAME.name());
                }
                stripper.getTextForRegion(Positions.LEFT.name());
                stripper.getTextForRegion(Positions.RIGHT.name());
                addingToList(stripper, Positions.LEFT.name(), kilometersServiceImpl);
                addingToList(stripper, Positions.RIGHT.name(), kilometersServiceImpl);

                if (currentPage == pages.getCount() - 1) {
                    int lastPageNumber = pages.getCount();
                    stripper.setStartPage(lastPageNumber);
                    stripper.setEndPage(lastPageNumber);
                    String text = stripper.getTextForRegion(Positions.TITLE.name());
                    gettingLastKilometer(text);
                }
                currentPage++;
            }
        } catch (IOException e) {
            throw new LoadingPdfException(e);
        }
        return kilometersServiceImpl;
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

        roadStatsServiceImpl.setLastKilometer(listDouble.get(listDouble.size() - 1));
    }


    private void addingToList(PDFTextStripperByArea stripper, String leftOrRight, KilometersServiceImpl kilometersServiceImpl) {
        List<String> kilometersFromColumn = getKilometerColumn(stripper, leftOrRight);
        kilometersServiceImpl.getAllKilometers().add(kilometersFromColumn);
    }

    private List<String> getKilometerColumn(PDFTextStripperByArea stripper, String leftOrRight) {
        return Arrays.stream(stripper.getTextForRegion(leftOrRight).split("\\s")).toList();
    }

    private void addRegionsConfiguration(PDFTextStripperByArea stripper) {
        stripper.addRegion(Positions.COMPANYNAME.name(), new Rectangle(59, 125, 302, 302));
        stripper.addRegion(Positions.BRUTTOANALYSIS.name(), new Rectangle(730, 41, 91, 548));
        stripper.addRegion(Positions.ANALYSIS.name(), new Rectangle(444, 25, 376, 18));
        stripper.addRegion(Positions.TITLE.name(), new Rectangle(12, 20, 389, 553));
        stripper.addRegion(Positions.LEFT.name(), new Rectangle(31, 45, 57, 518));
        stripper.addRegion(Positions.RIGHT.name(), new Rectangle(449, 45, 57, 518));
    }
}
