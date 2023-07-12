package org.pl.deenes.services;

import org.pl.deenes.configuration.SpringConfiguration;
import org.pl.deenes.infrastructure.repositories.LineEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest
@SpringJUnitConfig(classes = {SpringConfiguration.class})
class WOSReaderServiceMockTest {
    @Autowired
    private LineEntryRepository lineEntryRepository;

/*    @Test
    void testSavingToDB() {
        lineEntryRepository.save(LineDetails.builder().build());
        int size = lineEntryRepository.findAll().size();
        Assertions.assertEquals(1, size);

    }*/

}