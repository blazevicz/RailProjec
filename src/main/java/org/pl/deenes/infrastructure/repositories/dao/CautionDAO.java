package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.infrastructure.entity.CautionEntity;
import org.pl.deenes.model.Caution;

import java.util.List;

public interface CautionDAO {
    List<CautionEntity> saveAll(List<Caution> cautionList);
}
