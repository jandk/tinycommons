package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SeqSummaryTest {

    @Test
    void testSummaryInt() {
        // These do not have equals, so compare using toString
        assertThat(Seq.<Integer>empty().summary(Integer::intValue))
            .hasToString(new IntSummaryStatistics().toString());

        IntSummaryStatistics summary = Seq.of(1, 2, 3, 4, 5).summary(Integer::intValue);
        assertThat(summary.getCount()).isEqualTo(5);
        assertThat(summary.getSum()).isEqualTo(15);
        assertThat(summary.getMin()).isEqualTo(1);
        assertThat(summary.getMax()).isEqualTo(5);
        assertThat(summary.getAverage()).isEqualTo(3.0);
    }

    @Test
    void testSummaryLong() {
        // These do not have equals, so compare using toString
        assertThat(Seq.<Long>empty().summary(Long::longValue))
            .hasToString(new LongSummaryStatistics().toString());

        LongSummaryStatistics summary = Seq.of(1L, 2L, 3L, 4L, 5L).summary(Long::longValue);
        assertThat(summary.getCount()).isEqualTo(5);
        assertThat(summary.getSum()).isEqualTo(15L);
        assertThat(summary.getMin()).isEqualTo(1L);
        assertThat(summary.getMax()).isEqualTo(5L);
        assertThat(summary.getAverage()).isEqualTo(3.0);
    }

    @Test
    void testSummaryDouble() {
        // These do not have equals, so compare using toString
        assertThat(Seq.<Double>empty().summary(Double::doubleValue))
            .hasToString(new DoubleSummaryStatistics().toString());

        DoubleSummaryStatistics summary = Seq.of(1.0, 2.0, 3.0, 4.0, 5.0).summary(Double::doubleValue);
        assertThat(summary.getCount()).isEqualTo(5);
        assertThat(summary.getSum()).isEqualTo(15.0);
        assertThat(summary.getMin()).isEqualTo(1.0);
        assertThat(summary.getMax()).isEqualTo(5.0);
        assertThat(summary.getAverage()).isEqualTo(3.0);
    }

}
