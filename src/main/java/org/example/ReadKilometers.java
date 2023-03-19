package org.example;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDPage;
import org.pdfbox.util.PDFTextStripperByArea;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadKilometers {
    private static final String RIGHT = "kilometerColumnRight";
    private static final String LEFT = "kilometerColumnLeft";
    private final File file;

    public ReadKilometers(File file) {
        this.file = file;
    }

    public Kilometers reader() throws IOException {
        Kilometers kilometers = new Kilometers();
        PDDocument load = PDDocument.load(file);
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);




        final Rectangle rectRightPage = new Rectangle(449, 45, 57, 518);
        final Rectangle rectLeftPage = new Rectangle(29, 43, 57, 530);

        stripper.addRegion(RIGHT, rectRightPage);
        stripper.addRegion(LEFT, rectLeftPage);

        List<?> allPages = load.getDocumentCatalog().getAllPages();
        PDPage firstPage = (PDPage) allPages.get(0);
        stripper.extractRegions(firstPage);

        //TODO: wykrywanie kilometra z ostatniej linii - dodaj nowy triangle na przechwyt ostatniego kilometra

        for (int i = 0; i < allPages.size(); i++) {

            if (i != 0) {
                PDPage page = (PDPage) allPages.get(i);

                stripper.extractRegions(page);

                addingToList(stripper, LEFT, kilometers);
                addingToList(stripper, RIGHT, kilometers);

            } else {
                addingToList(stripper, RIGHT, kilometers);
            }
        }
        load.close();
        return kilometers;
    }

    private static void addingToList(PDFTextStripperByArea stripper, String leftOrRight, Kilometers kilometers) {
        String[] kilometerColumnRights = getKilometerColumnLefts(stripper, leftOrRight);
        kilometers.getAllKilometers().add(List.of(kilometerColumnRights));
    }

    private static String[] getKilometerColumnLefts(PDFTextStripperByArea stripper,  String leftOrRight) {
        return stripper.getTextForRegion(leftOrRight).split("\\s");
    }
}
