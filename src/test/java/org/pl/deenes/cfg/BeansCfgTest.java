package org.pl.deenes.cfg;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeansCfgTest {
    @Test
    void testObjectMapperConfiguration() {
        ObjectMapper objectMapper = BeansCfg.objectMapper();

        assertNotNull(objectMapper);
        assertFalse(objectMapper.getSerializationConfig().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
        assertFalse(objectMapper.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));

    }

}