package org.pl.deenes.infrastructure.repositories;

import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.entity.DriverEntity;
import org.pl.deenes.infrastructure.mapper.DriverMapper;
import org.pl.deenes.infrastructure.repositories.dao.DriverDAO;
import org.pl.deenes.infrastructure.repositories.jpa.DriverJpaRepository;
import org.pl.deenes.model.Driver;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DriverRepository implements DriverDAO {

    private final DriverMapper driverMapper;
    private final DriverJpaRepository driverJpaRepository;

    @Override
    public Optional<Driver> findDriverById(Integer id) {
        return driverJpaRepository.findById(id)
                .map(driverMapper::mapFromEntity);
    }

    @Override
    public Optional<Driver> findByPesel(String pesel) {
        return driverJpaRepository.findByPesel(pesel)
                .map(driverMapper::mapFromEntity);
    }

    @Override
    public Optional<Driver> findBySurname(String surname) {
        return driverJpaRepository.findBySurname(surname)
                .map(driverMapper::mapFromEntity);
    }

    @Override
    public Driver save(Driver driver) {
        DriverEntity save = driverJpaRepository.save(driverMapper.mapToEntity(driver));
        return driverMapper.mapFromEntity(save);
    }

    @Override
    public void delete(Integer driverId) {
        driverJpaRepository.deleteById(driverId);
    }

    @Override
    public void delete(Driver driver) {
        DriverEntity driverEntity = driverMapper.mapToEntity(driver);
        driverJpaRepository.delete(driverEntity);
    }

    @Override
    public List<Driver> findAllDrivers() {
        return driverJpaRepository.findAll().stream()
                .map(driverMapper::mapFromEntity)
                .toList();
    }
}

