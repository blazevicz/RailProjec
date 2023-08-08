package org.pl.deenes.services;

import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pl.deenes.model.Caution;

import java.util.stream.Stream;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WarningsReaderServiceImplTest {

    @InjectMocks
    private WarningsReaderServiceImpl warningsReaderService;

    private static @NonNull Stream<Arguments> provideStringsToPrepare() {
        return Stream.of(
                Arguments.of("-0,337 0,495 - 17 40 40  Vd) Linia 700;  poc. towarowe", "-0,337 0,495 - 17 40 40  Vd) Linia 700;  poc. towarowe"),
                Arguments.of("Częstochowa  Towarowa  2,450 2,650 Mały promień łuku 6 60 60 Linia 701", "2,450 2,650 Mały promień łuku 6 60 60 Linia 701"),
                Arguments.of("Poraj  9,820 11,063 - 4 90 90  Vd) Linia 155;  poc.  pasażerskie", "9,820 11,063 - 4 90 90  Vd) Linia 155;  poc.  pasażerskie"),
                Arguments.of("Łazy  280,600  Wjazd na grupę \"C\"  i rozrząd wagonów z  górki  2,  306,  308,  310,  312,  314,  316,  318,  320,  322  - 20  *)",
                        "280,600  Wjazd na grupę \"C\"  i rozrząd wagonów z  górki  2,  306,  308,  310,  312,  314,  316,  318,  320,  322  - 20  *)")
        );
    }

    private static @NonNull Stream<Arguments> providePreparedLines() {
        return Stream.of(
                Arguments.of("-0,337 0,495 - 17 40 40  Vd) Linia 700;  poc. towarowe",
                        Caution.builder().lineNumber(1).startFrom(-0.337).endOn(0.495).reason("-").trackNumber("17").leftTrack("40").rightTrack("40").comments("Vd) Linia 700;  poc. towarowe").build()),
                Arguments.of("2,450 2,650 Mały promień łuku 6 60 60 Linia 701",
                        Caution.builder().lineNumber(1).startFrom(2.450).endOn(2.650).reason("Mały promień łuku").trackNumber("6").leftTrack("60").rightTrack("60").comments("Linia 701").build()),
                Arguments.of("9,820 11,063 - 4 90 90  Vd) Linia 155;  poc.  pasażerskie",
                        Caution.builder().lineNumber(1).startFrom(9.820).endOn(11.063).reason("-").trackNumber("4").leftTrack("90").rightTrack("90").comments("Vd) Linia 155;  poc.  pasażerskie").build()),
                Arguments.of("280,600  Wjazd na grupę \"C\"  i rozrząd wagonów z  górki  2,  306,  308,  310,  312,  314,  316,  318,  320,  322  - 20  *)",
                        Caution.builder().lineNumber(1).startFrom(280.600).endOn(280.600).reason("Wjazd na grupę \"C\"  i rozrząd wagonów z  górki").trackNumber("2, 306, 308, 310, 312, 314, 316, 318, 320, 322").leftTrack("-").rightTrack("20").comments("*)").build())
        );
    }

    @ParameterizedTest
    @MethodSource("providePreparedLines")
    void shouldCreateCautionObjectCorrectly(String input, Caution caution) {
        Integer lineNumber = 1;

        Caution cautionAfterConversion = warningsReaderService.convertStringToCaution(input, lineNumber);

        Assertions.assertEquals(caution, cautionAfterConversion);
    }

    @ParameterizedTest
    @MethodSource("provideStringsToPrepare")
    void shouldPrepareLineCorrectly(String input, String output) {

        String result = warningsReaderService.prepareBeforeCreatingObjects(input);

        Assertions.assertEquals(output, result);
    }
}