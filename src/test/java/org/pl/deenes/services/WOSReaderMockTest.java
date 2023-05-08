package org.pl.deenes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pl.deenes.configuration.SpringConfiguration;
import org.pl.deenes.data.LineEntry;
import org.pl.deenes.repositories.LineEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest
@SpringJUnitConfig(classes = {SpringConfiguration.class})
class WOSReaderMockTest {
    @Autowired
    private LineEntryRepository lineEntryRepository;

    @Test
    void testSavingToDB() {
        lineEntryRepository.save(LineEntry.builder().build());
        int size = lineEntryRepository.findAll().size();

        Assertions.assertEquals(1, size);

    }

}