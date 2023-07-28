package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Line;
import org.pl.deenes.model.LineDetails;

import java.util.List;

public interface LineDAO {
    void saveALl(List<LineDetails> lineDetails);

    Line findLine(Integer lineNumber);
}
