package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class SeqAnyTest {

    @Test
    void testAny() {
        assertThat(Seq.empty().any()).isFalse();
        assertThat(Seq.of(1, 2, 3).any()).isTrue();
    }

    @Test
    void testAnyWithPredicate() {
        assertThat(Seq.of(1, 2, 3).any(i -> i == 2)).isTrue();
        assertThat(Seq.of(1, 2, 3).any(i -> i == 4)).isFalse();
        assertThat(Seq.<Integer>of().any(i -> i == 2)).isFalse();
    }

}
