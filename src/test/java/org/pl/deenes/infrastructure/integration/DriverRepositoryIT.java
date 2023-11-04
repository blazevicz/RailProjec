package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.pl.deenes.cfg.JwtService;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.infrastructure.repositories.TokenRepository;
import org.pl.deenes.model.Driver;
import org.pl.deenes.model.Role;
import org.pl.deenes.services.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(addFilters = false)
class DriverRepositoryIT extends IntegrationReposIT {

    private DriverRepository driverRepository;

    @MockBean
    private ResultServiceImpl resultService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private TokenRepository tokenRepository;


    @Test
    @Order(1)
    void shouldRemoveDriver() {
        List<Driver> allDrivers = driverRepository.findAllDrivers();

        Driver driver = allDrivers.get(0);

        driverRepository.delete(driver.getDriverId());
        List<Driver> allDriversAfterDeleteOne = driverRepository.findAllDrivers();
        System.out.println(allDriversAfterDeleteOne.size());
        Assertions.assertEquals(2, allDriversAfterDeleteOne.size());
    }

    @Test
    @Order(2)
    void shouldAddOneDriver() {
        Driver driver = Driver.builder()
                .name("A")
                .surname("B")
                .pesel("69070611222")
                .roles(Set.of(Role.builder().role("DRIVER").build()))
                .active(true)
                .password("123")
                .build();

        driverRepository.save(driver);
        List<Driver> allDrivers = driverRepository.findAllDrivers();
        Assertions.assertEquals(3, allDrivers.size());
    }

    @Test
    void shouldFindDriverById() {
        Optional<Driver> driverById = driverRepository.findDriverById(2);
        assertTrue(driverById.isPresent(), "Expected driver to be present");

    }
}
