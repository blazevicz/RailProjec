package org.pl.deenes.infrastructure.repositories.dao;

import org.pl.deenes.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverDAO {

    List<Driver> findAllDrivers();
    void delete(Integer driverId);
    Driver save(Driver driver);
    Optional<Driver> findDriverById(Integer id);

    Optional<Driver> findByPesel(String pesel);

    Optional<Driver> findBySurname(String surname);

}
