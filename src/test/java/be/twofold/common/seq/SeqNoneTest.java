package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class SeqNoneTest {

    @Test
    void testNone() {
        assertThat(Seq.empty().none()).isTrue();
        assertThat(Seq.of(1, 2, 3).none()).isFalse();
    }

    @Test
    void testNoneWithPredicate() {
        assertThat(Seq.of(1, 2, 3).none(i -> i == 2)).isFalse();
        assertThat(Seq.of(1, 2, 3).none(i -> i == 4)).isTrue();
        assertThat(Seq.<Integer>of().none(i -> i == 2)).isTrue();
    }

}
