package org.pl.deenes.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WOSReaderTest {
    @Autowired
    private WOSReader wosReader;

    @Test
    @Transactional
    @Rollback
    void testLoadWosPDFTransaction() throws IOException {
        wosReader.loadWosPDF();
        assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
    }


}