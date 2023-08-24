package org.pl.deenes.infrastructure.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AnalyseEntityTest {
    @Test
    void equalsAndHashCodeImplCheck() {
        AnalyseEntity analyse1 = AnalyseEntity.builder().trainKwr(123456).build();
        AnalyseEntity analyse2 = AnalyseEntity.builder().trainKwr(123456).build();
        AnalyseEntity analyse3 = AnalyseEntity.builder().trainKwr(654321).build();

        Assertions.assertThat(analyse1).isEqualTo(analyse2)
                .isNotEqualTo(analyse3);

        Assertions.assertThat(analyse1.hashCode()).hasSameHashCodeAs(analyse2.hashCode())
                .isNotEqualTo(analyse3.hashCode());
    }

    @Test
    void gettersAndSettersShouldWork() {
        AnalyseEntity analyse = new AnalyseEntity();
        analyse.setTrainAnalyseId(1);
        analyse.setTrainKwr(123456);
        analyse.setTrainMaxWeight(2000);

        Assertions.assertThat(analyse.getTrainAnalyseId()).isEqualTo(1);
        Assertions.assertThat(analyse.getTrainKwr()).isEqualTo(123456);
        Assertions.assertThat(analyse.getTrainMaxWeight()).isEqualTo(2000);
    }


}
