package org.pl.deenes;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MainTest {
    @Test
    void contextLoads() {
    }

    @Test
    void applicationStarts() {
        Main.main(new String[]{});
    }
}