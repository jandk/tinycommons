package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.function.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqAverageTest {

    @Test
    void testAverageInt() {
        assertThat(SequenceInteger.average(Integer::intValue)).isEqualTo(2.5);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyInteger.average(Integer::intValue));
    }

    @Test
    void testAverageIntOptional() {
        assertThat(SequenceInteger.averageOptional(Integer::intValue)).hasValue(2.5);
        assertThat(EmptyInteger.averageOptional(Integer::intValue)).isEmpty();
    }

    @Test
    void testAverageIntThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceInteger.average((ToIntFunction<Integer>) null));
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceInteger.averageOptional((ToIntFunction<Integer>) null));
    }


    @Test
    void testAverageLong() {
        assertThat(SequenceLong.average(Long::longValue)).isEqualTo(2.5);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyLong.average(Long::longValue));
    }

    @Test
    void testAverageLongOptional() {
        assertThat(SequenceLong.averageOptional(Long::longValue)).hasValue(2.5);
        assertThat(EmptyLong.averageOptional(Long::longValue)).isEmpty();
    }

    @Test
    void testAverageLongThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceLong.average((ToLongFunction<Long>) null));
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceLong.averageOptional((ToLongFunction<Long>) null));
    }

    @Test
    void testAverageDouble() {
        assertThat(SequenceDouble.average(Double::doubleValue)).isEqualTo(2.5);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyDouble.average(Double::doubleValue));
    }

    @Test
    void testAverageDoubleOptional() {
        assertThat(SequenceDouble.averageOptional(Double::doubleValue)).hasValue(2.5);
        assertThat(EmptyDouble.averageOptional(Double::doubleValue)).isEmpty();
    }

    @Test
    void testAverageDoubleThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceDouble.average((ToDoubleFunction<Double>) null));
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceDouble.averageOptional((ToDoubleFunction<Double>) null));
    }

}
