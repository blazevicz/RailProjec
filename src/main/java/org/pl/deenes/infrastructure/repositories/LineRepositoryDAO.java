package org.pl.deenes.infrastructure.repositories;

import org.pl.deenes.model.LineDetails;

import java.util.List;

public interface LineRepositoryDAO {

    void saveALl(List<LineDetails> lineDetails);
}
