package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.function.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqSumTest {

    @Test
    public void testSumOfInt() {
        assertThat(SequenceInteger.sum(Integer::intValue)).isEqualTo(10);

        assertThatNullPointerException()
            .isThrownBy(() -> Strings.sum((ToIntFunction<String>) null));
    }

    @Test
    public void testSumOfLong() {
        assertThat(SequenceLong.sum(Long::longValue)).isEqualTo(10L);

        assertThatNullPointerException()
            .isThrownBy(() -> Strings.sum((ToLongFunction<String>) null));
    }

    @Test
    public void testSumOfDouble() {
        assertThat(SequenceDouble.sum(Double::doubleValue)).isEqualTo(10.0);

        assertThatNullPointerException()
            .isThrownBy(() -> Strings.sum((ToDoubleFunction<String>) null));
    }

}
