package org.pl.deenes.services.interfaces;

import org.pl.deenes.services.TimetableDetails;

import java.io.IOException;

public interface Timetable {
    TimetableDetails read(String link) throws IOException;

}
