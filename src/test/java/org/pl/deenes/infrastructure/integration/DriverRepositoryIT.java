package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class DriverRepositoryIT extends IntegrationReposIT {

    private final DriverRepository driverRepository;

    @Test
    void shouldRemoveDriver() {
        List<Driver> allDrivers = driverRepository.findAllDrivers();

        Driver driver = allDrivers.get(0);

        driverRepository.delete(driver.getDriverId());
        List<Driver> allDriversAfterDeleteOne = driverRepository.findAllDrivers();
        Assertions.assertEquals(2, allDriversAfterDeleteOne.size());
    }

    @Test
    void shouldAddOneDriver() {
        Driver a = Driver.builder()
                .name("A")
                .surname("B")
                .pesel(123123)
                .build();

        driverRepository.save(a);
        List<Driver> allDrivers = driverRepository.findAllDrivers();
        Assertions.assertEquals(4, allDrivers.size());
    }

    @Test
    void shouldFindDriverById() {
        Optional<Driver> driverById = driverRepository.findDriverById(1);
        assertTrue(driverById.isPresent(), "Expected driver to be present");

    }
}
