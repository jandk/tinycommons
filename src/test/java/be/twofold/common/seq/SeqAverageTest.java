package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SeqAverageTest {

    @Test
    void testAverageInt() {
        assertThat(Seq.of(1, 2, 3, 4, 5).average(Integer::intValue)).isEqualTo(3.0);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.<Integer>empty().average(Integer::intValue));
    }

    @Test
    void testAverageIntOptional() {
        assertThat(Seq.of(1, 2, 3, 4, 5).averageOptional(Integer::intValue)).hasValue(3.0);
        assertThat(Seq.<Integer>empty().averageOptional(Integer::intValue)).isEmpty();
    }

    @Test
    void testAverageLong() {
        assertThat(Seq.of(1L, 2L, 3L, 4L, 5L).average(Long::longValue)).isEqualTo(3.0);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.<Long>empty().average(Long::longValue));
    }

    @Test
    void testAverageLongOptional() {
        assertThat(Seq.of(1L, 2L, 3L, 4L, 5L).averageOptional(Long::longValue)).hasValue(3.0);
        assertThat(Seq.<Long>empty().averageOptional(Long::longValue)).isEmpty();
    }

    @Test
    void testAverageDouble() {
        assertThat(Seq.of(1.0, 2.0, 3.0, 4.0, 5.0).average(Double::doubleValue)).isEqualTo(3.0);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.<Double>empty().average(Double::doubleValue));
    }

    @Test
    void testAverageDoubleOptional() {
        assertThat(Seq.of(1.0, 2.0, 3.0, 4.0, 5.0).averageOptional(Double::doubleValue)).hasValue(3.0);
        assertThat(Seq.<Double>empty().averageOptional(Double::doubleValue)).isEmpty();
    }

}
