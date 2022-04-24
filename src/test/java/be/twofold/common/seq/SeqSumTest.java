package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class SeqSumTest {

    @Test
    public void testSumOfInt() {
        assertThat(Seq.of(1, 2, 3).sum(Integer::intValue)).isEqualTo(6);
    }

    @Test
    public void testSumOfLong() {
        assertThat(Seq.of(1L, 2L, 3L).sum(Long::longValue)).isEqualTo(6L);
    }

    @Test
    public void testSumOfDouble() {
        assertThat(Seq.of(1.0, 2.0, 3.0).sum(Double::doubleValue)).isEqualTo(6.0);
    }

}
