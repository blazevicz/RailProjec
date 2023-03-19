package org.example;

import java.io.File;

public class Files {
    private  final File path;

    public Files(File path) {
        this.path = path;
    }

    static  File myPatch() {
        return new File("src/main/resources/RJ_SKRJ_666401_464028_9.pdf");
    }
}
