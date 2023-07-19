package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.DispatcherEntity;
import org.pl.deenes.infrastructure.mapper.DispatcherMapper;
import org.pl.deenes.infrastructure.repositories.dao.DispatcherDAO;
import org.pl.deenes.infrastructure.repositories.jpa.DispatcherJpaRepository;
import org.pl.deenes.model.Dispatcher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DispatcherRepository implements DispatcherDAO {

    private final DispatcherJpaRepository dispatcherJpaRepository;
    private final DispatcherMapper dispatcherMapper;

    @Override
    public Dispatcher save(Dispatcher dispatcher) {
        DispatcherEntity dispatcherEntity = dispatcherMapper.mapToEntity(dispatcher);
        DispatcherEntity save = dispatcherJpaRepository.save(dispatcherEntity);
        return dispatcherMapper.mapFromEntity(save);
    }

    @Override
    public List<Dispatcher> findAll() {
        return dispatcherJpaRepository.findAll()
                .stream().map(dispatcherMapper::mapFromEntity)
                .toList();
    }
}
