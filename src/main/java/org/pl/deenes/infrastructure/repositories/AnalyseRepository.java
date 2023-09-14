package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.expections.MethodNotImplementedException;
import org.pl.deenes.infrastructure.entity.AnalyseEntity;
import org.pl.deenes.infrastructure.mapper.AnalyseMapper;
import org.pl.deenes.infrastructure.mapper.CycleAvoidingMappingContext;
import org.pl.deenes.infrastructure.mapper.TrainMapper;
import org.pl.deenes.infrastructure.repositories.dao.AnalyseDAO;
import org.pl.deenes.infrastructure.repositories.jpa.AnalyseJpaRepository;
import org.pl.deenes.model.Analyse;
import org.pl.deenes.model.Train;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AnalyseRepository implements AnalyseDAO {
    private final AnalyseJpaRepository analyseJpaRepository;
    private final AnalyseMapper analyseMapper;
    private final TrainMapper trainMapper;

    @Override
    public Analyse save(Analyse analyse) {
        AnalyseEntity analyseEntity = analyseMapper.mapToEntity(analyse);
        AnalyseEntity savedAnalyse = analyseJpaRepository.save(analyseEntity);
        return analyseMapper.mapFromEntity(savedAnalyse, new CycleAvoidingMappingContext());
    }

    @Override
    public Train find(Integer trainKwr) {
        throw new MethodNotImplementedException("method not implemented");
    }

    @Override
    public List<Analyse> findAllByTrainKwr(Integer trainKwr) {
        List<AnalyseEntity> allByTrainKwr = analyseJpaRepository.findAllByTrainKwr(trainKwr);
        return allByTrainKwr.stream()
                .map(a -> analyseMapper.mapFromEntity(a, new CycleAvoidingMappingContext())).toList();
    }
}
