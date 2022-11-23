package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.function.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqSummaryTest {

    @Test
    void testSummaryInt() {
        // These do not have equals, so compare using toString
        assertThat(EmptyInteger.summary(Integer::intValue))
            .hasToString(new IntSummaryStatistics().toString());

        IntSummaryStatistics summary = SequenceInteger.summary(Integer::intValue);
        assertThat(summary.getCount()).isEqualTo(4);
        assertThat(summary.getSum()).isEqualTo(10);
        assertThat(summary.getMin()).isEqualTo(1);
        assertThat(summary.getMax()).isEqualTo(4);
        assertThat(summary.getAverage()).isEqualTo(2.5);

        assertThatNullPointerException()
            .isThrownBy(() -> Strings.summary((ToIntFunction<String>) null));
    }

    @Test
    void testSummaryLong() {
        // These do not have equals, so compare using toString
        assertThat(EmptyLong.summary(Long::longValue))
            .hasToString(new LongSummaryStatistics().toString());

        LongSummaryStatistics summary = SequenceLong.summary(Long::longValue);
        assertThat(summary.getCount()).isEqualTo(4);
        assertThat(summary.getSum()).isEqualTo(10L);
        assertThat(summary.getMin()).isEqualTo(1L);
        assertThat(summary.getMax()).isEqualTo(4L);
        assertThat(summary.getAverage()).isEqualTo(2.5);

        assertThatNullPointerException()
            .isThrownBy(() -> Strings.summary((ToLongFunction<String>) null));
    }

    @Test
    void testSummaryDouble() {
        // These do not have equals, so compare using toString
        assertThat(EmptyDouble.summary(Double::doubleValue))
            .hasToString(new DoubleSummaryStatistics().toString());

        DoubleSummaryStatistics summary = SequenceDouble.summary(Double::doubleValue);
        assertThat(summary.getCount()).isEqualTo(4);
        assertThat(summary.getSum()).isEqualTo(10.0);
        assertThat(summary.getMin()).isEqualTo(1.0);
        assertThat(summary.getMax()).isEqualTo(4.0);
        assertThat(summary.getAverage()).isEqualTo(2.5);

        assertThatNullPointerException()
            .isThrownBy(() -> Strings.summary((ToDoubleFunction<String>) null));
    }

}
